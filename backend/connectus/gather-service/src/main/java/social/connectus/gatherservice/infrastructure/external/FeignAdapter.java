package social.connectus.gatherservice.infrastructure.external;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import social.connectus.gatherservice.application.rest.request.CreateSpotRequest;
import social.connectus.gatherservice.application.rest.request.GetSpotRequest;
import social.connectus.gatherservice.application.rest.response.CreateSpotResponse;
import social.connectus.gatherservice.application.rest.response.GetSpotResponse;

@Component
@RequiredArgsConstructor
public class FeignAdapter implements SpotPort{
    private final SpotClient spotClient;

    // TODO: 위치 서비스와 연결하여 특정 요소 추가 메서드 실행.
    public CreateSpotResponse createSpot(CreateSpotRequest request){
        return spotClient.createSpot(request);
    }
    public GetSpotResponse getSpot(GetSpotRequest request) {
        return spotClient.getSpot(request);
    }
}
