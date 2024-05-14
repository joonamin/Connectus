package social.connectus.gatherservice.application.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.gatherservice.common.type.Type;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSpotRequest {
    private double latitude;
    private double longitude;
    private Type type;
//    private long domainId;
    // domainId는 위치 생성 후 도메인 생성 후에 전달한다.
}
