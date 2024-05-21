package social.connectus.gatherservice.infrastructure.external;

import social.connectus.gatherservice.application.rest.request.CreateSpotRequest;
import social.connectus.gatherservice.application.rest.request.GetSpotRequest;
import social.connectus.gatherservice.application.rest.response.CreateSpotResponse;
import social.connectus.gatherservice.application.rest.response.GetSpotResponse;

public interface SpotPort {
    CreateSpotResponse createSpot(CreateSpotRequest request);
    GetSpotResponse getSpot(GetSpotRequest request);
}
