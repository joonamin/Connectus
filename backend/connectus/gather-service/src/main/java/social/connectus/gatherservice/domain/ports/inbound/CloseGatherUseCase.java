package social.connectus.gatherservice.domain.ports.inbound;

import social.connectus.gatherservice.application.rest.request.CloseGatherRequest;
import social.connectus.gatherservice.common.exception.ClosedGatherException;
import social.connectus.gatherservice.common.exception.InvalidHostIdException;
import social.connectus.gatherservice.common.exception.ResourceNotFoundException;

public interface CloseGatherUseCase {
    void closeGather(CloseGatherRequest closeGatherRequest) throws ResourceNotFoundException, ClosedGatherException, InvalidHostIdException;
}
