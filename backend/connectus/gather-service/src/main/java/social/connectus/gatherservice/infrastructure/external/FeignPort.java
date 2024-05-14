package social.connectus.gatherservice.infrastructure.external;

import social.connectus.gatherservice.application.rest.request.CreateSpotRequest;
import social.connectus.gatherservice.application.rest.request.UpdateSpotRequest;

public interface FeignPort {
    long CreateSpot(CreateSpotRequest request);
    void UpdateSpot(UpdateSpotRequest request);
}
