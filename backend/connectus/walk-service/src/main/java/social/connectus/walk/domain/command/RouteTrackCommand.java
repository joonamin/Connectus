package social.connectus.walk.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.application.rest.request.RouteTrackRequest;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteTrackCommand {
    private long walkId;
    private long userId;

    public static RouteTrackCommand from(RouteTrackRequest request){
        return RouteTrackCommand.builder()
                .walkId(request.getWalkId())
                .userId(request.getUserId())
                .build();
    }
}
