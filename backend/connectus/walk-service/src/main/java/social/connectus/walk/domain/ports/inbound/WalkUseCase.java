package social.connectus.walk.domain.ports.inbound;

import social.connectus.walk.application.rest.response.CreateWalkResponse;
import social.connectus.walk.common.customannotations.UseCase;
import social.connectus.walk.domain.command.CreateWalkCommand;

public interface WalkUseCase {
    public CreateWalkResponse createWalk(CreateWalkCommand command);
}
