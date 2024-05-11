package social.connectus.walk.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.application.rest.request.RouteShareRequest;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteShareCommand {
    private long walkId;
    private long userId;

    public static RouteShareCommand from (RouteShareRequest request){
        return RouteShareCommand.builder()
                .walkId(request.getWalkId())
                .userId(request.getUserId())
                .build();
    }
}
