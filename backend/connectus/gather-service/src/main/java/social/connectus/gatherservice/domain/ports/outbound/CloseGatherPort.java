package social.connectus.gatherservice.domain.ports.outbound;

import social.connectus.gatherservice.application.rest.request.CloseGatherRequest;
import social.connectus.gatherservice.domain.model.Gather;

public interface CloseGatherPort {
    void closeGather(CloseGatherRequest request);
}
