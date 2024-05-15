package social.connectus.gatherservice.infrastructure.external;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import social.connectus.gatherservice.application.rest.request.CreateSpotRequest;
import social.connectus.gatherservice.application.rest.request.UpdateSpotRequest;

import java.util.List;

@org.springframework.cloud.openfeign.FeignClient(name = "spot-service")
public interface SpotClient {
    @PostMapping("")
    long CreateSpot(CreateSpotRequest request);
    @PutMapping("")
    void UpdateSpot(UpdateSpotRequest request);
}
