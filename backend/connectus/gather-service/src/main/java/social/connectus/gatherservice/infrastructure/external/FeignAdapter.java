package social.connectus.gatherservice.infrastructure.external;

import social.connectus.gatherservice.application.rest.request.CreateSpotRequest;
import social.connectus.gatherservice.application.rest.request.UpdateSpotRequest;

public class FeignAdapter implements FeignPort{

    // TODO: 위치 서비스와 연결하여 특정 요소 추가 메서드 실행.
    public long CreateSpot(CreateSpotRequest request){
        return 0;
    }
    @Override
    public void UpdateSpot(UpdateSpotRequest request) {
        return;
    }
}
