package social.connectus.walk.application.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteProtectRequest {
    private long walkId;
    private long userId;
}
