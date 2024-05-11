package social.connectus.gatherservice.domain.ports.inbound;

import social.connectus.gatherservice.application.rest.request.JoinGatherRequest;
import social.connectus.gatherservice.application.rest.request.WantJoinGatherRequest;
import social.connectus.gatherservice.application.rest.response.CreateGatherResponse;
import social.connectus.gatherservice.application.rest.response.GetGatherResponse;
import social.connectus.gatherservice.common.exception.*;
import social.connectus.gatherservice.domain.command.CloseGatherCommand;
import social.connectus.gatherservice.domain.command.CreateGatherCommand;

public interface GatherUseCase {
    CreateGatherResponse createGather(CreateGatherCommand command);
    GetGatherResponse getGather(long gatherId) throws ResourceNotFoundException;
    void wantJoinGather(WantJoinGatherRequest wantJoinGatherRequest) throws ResourceNotFoundException, GlobalException, ClosedGatherException, AlreadyJoinedException;
    void closeGather(CloseGatherCommand closeGatherRequest) throws ResourceNotFoundException, ClosedGatherException, InvalidHostIdException;
    boolean joinGather(JoinGatherRequest request) throws ResourceNotFoundException, ClosedGatherException, AlreadyJoinedException, InvalidHostIdException;
}
