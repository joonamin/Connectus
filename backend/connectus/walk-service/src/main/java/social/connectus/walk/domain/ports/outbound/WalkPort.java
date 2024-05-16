package social.connectus.walk.domain.ports.outbound;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Slice;

import social.connectus.walk.common.utils.SliceResponse;
import social.connectus.walk.domain.command.*;
import social.connectus.walk.domain.model.entity.Post;
import social.connectus.walk.domain.model.entity.Route;
import social.connectus.walk.domain.model.entity.Walk;

import java.util.List;

public interface WalkPort {

    @Transactional
    Walk createWalk(Walk walk);

    void createPostList(List<Post> postList, Walk walk);

    void createRoute(List<Route> routes, Walk walk);

    void routeLike(RouteLikeCommand command);

    Walk getWalkById(long walkId);

    List<Walk> getWalkByUser(long userId);

    void routeShare(RouteShareCommand command);

    void routeTrack(RouteTrackCommand command);

    void routeProtect(RouteProtectCommand command);

    SliceResponse<Long> getWalkIdsByPosition(GetWalksByPositionCommand command);

    Slice<Walk> getWalksByPosition(GetWalksByPositionCommand command);
}
