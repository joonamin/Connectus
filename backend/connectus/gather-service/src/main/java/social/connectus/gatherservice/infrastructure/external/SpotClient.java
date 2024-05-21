package social.connectus.gatherservice.infrastructure.external;

import org.springframework.web.bind.annotation.PostMapping;
import social.connectus.gatherservice.application.rest.request.CreateSpotRequest;
import social.connectus.gatherservice.application.rest.request.GetSpotRequest;
import social.connectus.gatherservice.application.rest.response.CreateSpotResponse;
import social.connectus.gatherservice.application.rest.response.GetSpotResponse;

@org.springframework.cloud.openfeign.FeignClient(name = "spot-service")
public interface SpotClient {
    @PostMapping("/spot/insert")
    CreateSpotResponse createSpot(CreateSpotRequest request);
    @PostMapping("/spot/get")
    GetSpotResponse getSpot(GetSpotRequest request);
}
