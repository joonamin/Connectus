package social.connectus.domain.ports.inbound;

import social.connectus.common.exception.ResourceNotFoundException;
import social.connectus.domain.model.Gather;

public interface GetGatherUseCase {
    Gather getGather(long gatherId) throws ResourceNotFoundException;
}
