package social.connectus.walk.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.application.rest.request.RouteLikeRequest;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteLikeCommand {
    private long walkId;
    private long userId;

    public static RouteLikeCommand from(RouteLikeRequest request){
        return RouteLikeCommand.builder()
                .walkId(request.getWalkId())
                .userId(request.getUserId())
                .build();
    }
}
