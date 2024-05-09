package social.connectus.walk.application.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetWalksByPositionRequest {
    private double latitude;
    private double longitude;
    private double distance;
    private long userId;
    private int pageNumber;
    private int pageSize;
}
