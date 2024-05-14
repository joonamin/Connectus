package social.connectus.gatherservice.infrastructure.external;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import social.connectus.gatherservice.application.rest.request.CreateSpotRequest;
import social.connectus.gatherservice.application.rest.request.UpdateSpotRequest;

@Component
@RequiredArgsConstructor
public class FeignAdapter implements FeignPort{
    private final SpotClient spotClient;

    // TODO: 위치 서비스와 연결하여 특정 요소 추가 메서드 실행.
    public long CreateSpot(CreateSpotRequest request){
        return spotClient.CreateSpot(request);
    }
    @Override
    public void UpdateSpot(UpdateSpotRequest request) {
        spotClient.UpdateSpot(request);
    }
}
