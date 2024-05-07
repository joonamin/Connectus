package social.connectus.walk.infrastructure.databases.mariadb;

import social.connectus.walk.domain.command.CreateWalkCommand;
import social.connectus.walk.domain.model.entity.Walk;
import social.connectus.walk.domain.ports.outbound.WalkPort;

public class WalkAdapter implements WalkPort {
    @Override
    public Walk createWalk(CreateWalkCommand command) {
        return null;
    }
}
