package social.connectus.walk.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.application.rest.request.RouteProtectRequest;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteProtectCommand {
    private long walkId;
    private long userId;

    public static RouteProtectCommand from(RouteProtectRequest request){
        return RouteProtectCommand.builder()
                .walkId(request.getWalkId())
                .userId(request.getUserId())
                .build();
    }
}
