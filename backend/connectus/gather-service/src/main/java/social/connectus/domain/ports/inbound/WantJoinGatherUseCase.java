package social.connectus.domain.ports.inbound;

import social.connectus.application.rest.request.CloseGatherRequest;
import social.connectus.application.rest.request.WantJoinGatherRequest;
import social.connectus.common.exception.ClosedGatherException;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.exception.ResourceNotFoundException;

public interface WantJoinGatherUseCase {
    void wantJoinGather(WantJoinGatherRequest wantJoinGatherRequest) throws ResourceNotFoundException, GlobalException, ClosedGatherException;
}
