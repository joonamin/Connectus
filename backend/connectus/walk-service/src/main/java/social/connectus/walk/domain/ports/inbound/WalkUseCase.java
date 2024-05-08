package social.connectus.walk.domain.ports.inbound;

import social.connectus.walk.application.rest.response.CreateWalkResponse;
import social.connectus.walk.domain.command.CreateWalkCommand;
import social.connectus.walk.domain.command.RouteLikeCommand;
import social.connectus.walk.domain.command.RouteShareCommand;
import social.connectus.walk.domain.command.RouteTrackCommand;
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
}
