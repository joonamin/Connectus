package social.connectus.walk.domain.ports.outbound;

import org.springframework.data.domain.Slice;
import social.connectus.walk.domain.command.*;
import social.connectus.walk.domain.model.entity.Walk;

import java.util.List;

public interface WalkPort {
    String feignHealthCheck();
    Walk createWalk(CreateWalkCommand command);
    void routeLike(RouteLikeCommand command);

    Walk getWalkById(long walkId);

    List<Walk> getWalkByUser(long userId);

    void routeShare(RouteShareCommand command);

    void routeTrack(RouteTrackCommand command);

    void routeProtect(RouteProtectCommand command);

    Slice<Long> getWalksByPosition(GetWalksByPositionCommand command);
}
