package social.connectus.gatherservice.domain.ports.inbound;

import social.connectus.gatherservice.application.rest.response.GetGatherResponse;
import social.connectus.gatherservice.common.exception.ResourceNotFoundException;
import social.connectus.gatherservice.domain.model.Gather;

public interface GetGatherUseCase {
    GetGatherResponse getGather(long gatherId) throws ResourceNotFoundException;
}
