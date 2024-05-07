package social.connectus.walk.domain.ports.outbound;

import social.connectus.walk.domain.command.CreateWalkCommand;
import social.connectus.walk.domain.model.entity.Walk;

public interface WalkPort {
    public String feignHealthCheck();
    public Walk createWalk(CreateWalkCommand command);
}
