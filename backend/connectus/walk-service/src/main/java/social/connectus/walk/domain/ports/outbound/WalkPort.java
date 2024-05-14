package social.connectus.walk.domain.ports.outbound;

import org.springframework.data.domain.Slice;
import social.connectus.walk.domain.command.*;
import social.connectus.walk.domain.model.entity.CompletedAchievement;
import social.connectus.walk.domain.model.entity.Post;
import social.connectus.walk.domain.model.entity.Route;
import social.connectus.walk.domain.model.entity.Walk;

import java.util.List;
import java.util.Set;

public interface WalkPort {
    Walk createWalk(CreateWalkCommand command);

    void createPostList(List<Post> postList, Walk walk);

    void createRoute(List<Route> routes, Walk walk);

    void createAchievement(Set<CompletedAchievement> completedAchievements, Walk walk);

    void routeLike(RouteLikeCommand command);

    Walk getWalkById(long walkId);

    List<Walk> getWalkByUser(long userId);

    void routeShare(RouteShareCommand command);

    void routeTrack(RouteTrackCommand command);

    void routeProtect(RouteProtectCommand command);

    Slice<Long> getWalksByPosition(GetWalksByPositionCommand command);
}
