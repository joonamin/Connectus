package social.connectus.gatherservice.domain.ports.inbound;

import social.connectus.gatherservice.application.rest.request.WantJoinGatherRequest;
import social.connectus.gatherservice.common.exception.ClosedGatherException;
import social.connectus.gatherservice.common.exception.GlobalException;
import social.connectus.gatherservice.common.exception.ResourceNotFoundException;

public interface WantJoinGatherUseCase {
    void wantJoinGather(WantJoinGatherRequest wantJoinGatherRequest) throws ResourceNotFoundException, GlobalException, ClosedGatherException;
}
