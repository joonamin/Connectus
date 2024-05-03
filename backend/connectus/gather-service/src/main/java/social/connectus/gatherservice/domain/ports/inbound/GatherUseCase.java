package social.connectus.gatherservice.domain.ports.inbound;

import social.connectus.gatherservice.application.rest.request.CloseGatherRequest;
import social.connectus.gatherservice.application.rest.request.JoinGatherRequest;
import social.connectus.gatherservice.application.rest.request.WantJoinGatherRequest;
import social.connectus.gatherservice.application.rest.response.GetGatherResponse;
import social.connectus.gatherservice.common.exception.*;
import social.connectus.gatherservice.domain.model.Gather;

public interface GatherUseCase {
    void createGather(Gather gather);
    GetGatherResponse getGather(long gatherId) throws ResourceNotFoundException;
    void wantJoinGather(WantJoinGatherRequest wantJoinGatherRequest) throws ResourceNotFoundException, GlobalException, ClosedGatherException, AlreadyJoinedException;
    void closeGather(CloseGatherRequest closeGatherRequest) throws ResourceNotFoundException, ClosedGatherException, InvalidHostIdException;
    boolean joinGather(JoinGatherRequest request) throws ResourceNotFoundException, ClosedGatherException, AlreadyJoinedException, InvalidHostIdException;
}
