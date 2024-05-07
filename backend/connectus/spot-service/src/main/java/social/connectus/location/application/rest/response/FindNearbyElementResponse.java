package social.connectus.location.application.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.location.common.type.Ping;

import java.util.ArrayList;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindNearbyElementResponse {
    ArrayList<Ping> nearby;
    ArrayList<Ping> distance;
}
