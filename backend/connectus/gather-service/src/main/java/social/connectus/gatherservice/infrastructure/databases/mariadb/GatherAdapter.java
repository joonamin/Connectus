package social.connectus.gatherservice.infrastructure.databases.mariadb;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import social.connectus.gatherservice.application.rest.request.CloseGatherRequest;
import social.connectus.gatherservice.common.constants.GatherConstants;
import social.connectus.gatherservice.common.exception.ResourceNotFoundException;
import social.connectus.gatherservice.domain.command.JoinGatherCommand;
import social.connectus.gatherservice.domain.command.WantJoinGatherCommand;
import social.connectus.gatherservice.domain.model.Gather;
import social.connectus.gatherservice.domain.ports.outbound.GatherPort;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GatherAdapter implements GatherPort {
    @Autowired
    private final GatherRepository gatherRepository;

    @Override
    public Optional<Gather> getGatherById(long gatherId) {
        return gatherRepository.findById(gatherId);
//                .orElseThrow(()-> new ResourceNotFoundException())

//        ModelMapper modelMapper = new ModelMapper();
//        GetGatherResponse getGatherResponse = modelMapper.map(gather, GetGatherResponse.class);
//        for(Candidate candidate : gather.getCandidateList()){
//            long userId = candidate.getUserId();
//            getGatherResponse.getCandidateList().add(userId);
//        }
//        return getGatherResponse;
    }

    @Override
    public void closeGather(CloseGatherRequest request) throws ResourceNotFoundException {
        long gatherId = request.getGatherId();
        Gather beforeUpdate = gatherRepository.findById(gatherId)
                .orElseThrow(()-> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));
        // gather의 closed를 true로 변경
        Gather gather = Gather.builder()
                .id(request.getGatherId())
                .hostId(request.getUserId())
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
    public void createGather(Gather gather) {
        gatherRepository.save(gather);
    }

    @Override
    public void wantJoinGather(WantJoinGatherCommand command) throws ResourceNotFoundException {
        long gatherId = command.getGatherId();
        long userId = command.getUserId();
        Gather gather = gatherRepository.findById(gatherId)
                .orElseThrow(()-> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));
        gather.getCandidateList().add(userId);
        gatherRepository.save(gather);
    }

    @Override
    public void joinGather(JoinGatherCommand command) throws ResourceNotFoundException {
        long gatherId = command.getGatherId();
        long userId = command.getUserId();
        Gather gather = gatherRepository.findById(gatherId)
                .orElseThrow(()-> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));
        gather.getCandidateList().remove(userId);
        gather.getJoinerList().add(userId);
        gatherRepository.save(gather);
    }
}
