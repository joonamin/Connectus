package social.connectus.gatherservice.infrastructure.databases.mariadb;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import social.connectus.gatherservice.application.rest.request.CreateSpotRequest;
import social.connectus.gatherservice.application.rest.request.UpdateSpotRequest;
import social.connectus.gatherservice.application.rest.response.CreateGatherResponse;
import social.connectus.gatherservice.common.constants.GatherConstants;
import social.connectus.gatherservice.common.exception.ResourceNotFoundException;
import social.connectus.gatherservice.common.type.Type;
import social.connectus.gatherservice.domain.command.CloseGatherCommand;
import social.connectus.gatherservice.domain.command.CreateGatherCommand;
import social.connectus.gatherservice.domain.command.JoinGatherCommand;
import social.connectus.gatherservice.domain.command.WantJoinGatherCommand;
import social.connectus.gatherservice.domain.model.Gather;
import social.connectus.gatherservice.domain.ports.outbound.GatherPort;
import social.connectus.gatherservice.infrastructure.external.FeignPort;

@Component
@RequiredArgsConstructor
public class GatherAdapter implements GatherPort {
    private final ModelMapper modelMapper;
    private final GatherRepository gatherRepository;
    private final FeignPort feignPort;

    @Override
    public Gather getGatherById(long gatherId) {
        return gatherRepository.findById(gatherId)
                .orElseThrow(()-> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));
    }

    @Override
    @Transactional
    public void closeGather(CloseGatherCommand command) throws ResourceNotFoundException {
        long gatherId = command.getGatherId();
        Gather beforeUpdate = gatherRepository.findById(gatherId)
                .orElseThrow(() -> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));
        // gather의 closed를 true로 변경
        Gather gather = Gather.builder()
                .id(command.getGatherId())
                .hostId(beforeUpdate.getHostId())
                .isClosed(true)
                .content(beforeUpdate.getContent())
                .endTime(beforeUpdate.getEndTime())
                .maxJoiner(beforeUpdate.getMaxJoiner())
                .candidateList(beforeUpdate.getCandidateList())
                .joinerList(beforeUpdate.getJoinerList())
                .build();

        gatherRepository.save(gather);
    }

    @Override
    @Transactional
    public CreateGatherResponse createGather(CreateGatherCommand command) {
        // TODO: 모여라와 위치 연결 확인
        long spotId = feignPort.CreateSpot(CreateSpotRequest.builder()
                        .latitude(command.getLatitude())
                        .longitude(command.getLongitude())
                        .type(Type.GATHER)
                        .build());

        Gather gather =  Gather.builder()
                .hostId(command.getHostId())
                .content(command.getContent())
                .spotId(spotId)
                .maxJoiner(command.getMaxJoiner())
                .endTime(command.getEndTime())
                .build();
        gatherRepository.save(gather);
        
        feignPort.UpdateSpot(UpdateSpotRequest.builder()
                        .spotId(spotId)
                        .domainId(gather.getId())
                        .build());
        return CreateGatherResponse.from(gather);
    }

    @Override
    @Transactional
    public void wantJoinGather(WantJoinGatherCommand command) throws ResourceNotFoundException {
        long gatherId = command.getGatherId();
        long userId = command.getUserId();
        Gather gather = gatherRepository.findById(gatherId)
                .orElseThrow(() -> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));
        gather.getCandidateList().add(userId);
        gatherRepository.save(gather);
    }

    @Override
    @Transactional
    public void joinGather(JoinGatherCommand command) throws ResourceNotFoundException {
        long gatherId = command.getGatherId();
        long userId = command.getUserId();
        Gather gather = gatherRepository.findById(gatherId)
                .orElseThrow(() -> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));
        gather.getCandidateList().remove(userId);
        gather.getJoinerList().add(userId);
        gatherRepository.save(gather);
    }
}
