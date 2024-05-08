package social.connectus.walk.domain.ports.outbound;

import social.connectus.walk.domain.command.CreateWalkCommand;
import social.connectus.walk.domain.command.RouteLikeCommand;
import social.connectus.walk.domain.model.entity.Walk;

public interface WalkPort {
    String feignHealthCheck();
    Walk createWalk(CreateWalkCommand command);
    void routeLike(RouteLikeCommand command);
}
