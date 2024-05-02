package social.connectus.gatherservice.domain.ports.outbound;

import social.connectus.gatherservice.application.rest.response.GetGatherResponse;
import social.connectus.gatherservice.common.exception.ResourceNotFoundException;
import social.connectus.gatherservice.domain.model.Gather;

import java.util.Optional;

public interface GetGatherIdPort {
    Optional<Gather> getGatherById(long gatherId) throws ResourceNotFoundException;
}
