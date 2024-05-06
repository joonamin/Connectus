package social.connectus.gatherservice.domain.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import social.connectus.gatherservice.application.rest.request.CloseGatherRequest;
import social.connectus.gatherservice.application.rest.request.JoinGatherRequest;
import social.connectus.gatherservice.application.rest.request.WantJoinGatherRequest;
import social.connectus.gatherservice.application.rest.response.CreateGatherResponse;
import social.connectus.gatherservice.application.rest.response.GetGatherResponse;
import social.connectus.gatherservice.common.constants.GatherConstants;
import social.connectus.gatherservice.common.customannotations.UseCase;
import social.connectus.gatherservice.common.exception.*;
import social.connectus.gatherservice.domain.command.CloseGatherCommand;
import social.connectus.gatherservice.domain.command.CreateGatherCommand;
import social.connectus.gatherservice.domain.command.JoinGatherCommand;
import social.connectus.gatherservice.domain.command.WantJoinGatherCommand;
import social.connectus.gatherservice.domain.model.Gather;
import social.connectus.gatherservice.domain.ports.inbound.GatherUseCase;
import social.connectus.gatherservice.domain.ports.outbound.GatherPort;

@AllArgsConstructor
@UseCase
public class GatherService implements GatherUseCase {

    private final GatherPort gatherPort;
    private final ModelMapper modelMapper;
    @Override
    public void closeGather(CloseGatherCommand command) throws ResourceNotFoundException, ClosedGatherException, InvalidHostIdException {
        long gatherId = command.getGatherId();
        long userId = command.getUserId();
        // gather의 주최자가 user_id인지 확인
        Gather gather = gatherPort.getGatherById(gatherId)
                        .orElseThrow(()-> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));

        if(gather.isClosed())
            throw new ClosedGatherException(GatherConstants.ALREADY_CLOSED_GATHER + gatherId);
        if(gather.getHostId() != userId)
            throw new InvalidHostIdException(GatherConstants.INVALID_HOST_ID + userId);

        gatherPort.closeGather(command);
    }

    @Override
    public CreateGatherResponse createGather(CreateGatherCommand command) {
        return gatherPort.createGather(command);
    }

    @Override
    public GetGatherResponse getGather(long gatherId) throws ResourceNotFoundException {
        Gather gather = gatherPort.getGatherById(gatherId)
                .orElseThrow(() -> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));
        return modelMapper.map(gather, GetGatherResponse.class);
    }

    @Override
    public void wantJoinGather(WantJoinGatherRequest request) throws ResourceNotFoundException, ClosedGatherException, GlobalException, AlreadyJoinedException {
        long gatherId = request.getGatherId();
        long userId = request.getUserId();

        Gather gather = gatherPort.getGatherById(gatherId)
                .orElseThrow(()-> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));
        if(gather.isClosed())
            throw new ClosedGatherException(GatherConstants.ALREADY_CLOSED_GATHER + gatherId);
        // 유저가 호스트, 참여희망자, 참여자라면 exception 발생
        if(gather.getHostId() == userId
                || gather.getCandidateList().contains(userId)
                || gather.getJoinerList().contains(userId))
            throw new AlreadyJoinedException(GatherConstants.IS_ALEADY_JOINED);

        WantJoinGatherCommand command = modelMapper.map(request, WantJoinGatherCommand.class);
        gatherPort.wantJoinGather(command);
    }
    @Override
    public boolean joinGather(JoinGatherRequest request) throws ResourceNotFoundException, ClosedGatherException, AlreadyJoinedException, InvalidHostIdException {
        long gatherId = request.getGatherId();
        long userId = request.getUserId();

        // Validation
        Gather gather = gatherPort.getGatherById(gatherId)
                .orElseThrow(()-> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));
        if(gather.isClosed())
            throw new ClosedGatherException(GatherConstants.ALREADY_CLOSED_GATHER + gatherId);
        // 유저가 호스트, 참여자라면 exception 발생
        if(gather.getHostId() == userId
                || gather.getJoinerList().contains(userId))
            throw new AlreadyJoinedException(GatherConstants.IS_ALEADY_JOINED);

        JoinGatherCommand command = modelMapper.map(request, JoinGatherCommand.class);
        gatherPort.joinGather(command);

        // 모집 최대 인원 시 자동 모집 완료 처리
        gather = gatherPort.getGatherById(gatherId)
                .orElseThrow(()-> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));
        if(gather.getJoinerList().size() == gather.getMaxJoiner()){
            CloseGatherCommand closeGatherCommand = modelMapper.map(request, CloseGatherCommand.class);
            closeGather(closeGatherCommand);
            return true;
        }
        return false;
    }
}
