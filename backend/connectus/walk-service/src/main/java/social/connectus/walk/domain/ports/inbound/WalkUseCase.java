package social.connectus.walk.domain.ports.inbound;

import org.springframework.data.domain.Slice;
import social.connectus.walk.application.rest.response.AchievementResponse;
import social.connectus.walk.application.rest.response.CreateWalkResponse;
import social.connectus.walk.domain.command.*;
import social.connectus.walk.domain.model.entity.Walk;

import java.util.List;

public interface WalkUseCase {
    CreateWalkResponse createWalk(CreateWalkCommand command);

    String feignHealthCheck();


    void routeLike(RouteLikeCommand from);

    Walk getWalkById(long walkId);

    List<Walk> getWalkByUser(long userId);

    void routeShare(RouteShareCommand command);

    void routeTrack(RouteTrackCommand command);

    void routeProtect(RouteProtectCommand command);

    Slice<Long> getWalkIdsByPosition(GetWalksByPositionCommand command);

    double getDistance(double latStart, double lonStart, double latEnd, double lonEnd);

    List<AchievementResponse> getAchievementsByWalk(Long userId, GetAchievementsCommand command);

    Slice<Walk> getWalksByPosition(GetWalksByPositionCommand from);
}
