package social.connectus.walk.domain.ports.inbound;

import social.connectus.walk.application.rest.response.CreateWalkResponse;
import social.connectus.walk.common.customannotations.UseCase;
import social.connectus.walk.domain.command.CreateWalkCommand;
import social.connectus.walk.domain.command.RouteLikeCommand;

public interface WalkUseCase {
    CreateWalkResponse createWalk(CreateWalkCommand command);

    String feignHealthCheck();


    void routeLike(RouteLikeCommand from);
}
