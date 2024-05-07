package social.connectus.walk.domain.services;

import lombok.RequiredArgsConstructor;
import social.connectus.walk.application.rest.response.CreateWalkResponse;
import social.connectus.walk.common.customannotations.UseCase;
import social.connectus.walk.domain.command.CreateWalkCommand;
import social.connectus.walk.domain.model.entity.Walk;
import social.connectus.walk.domain.ports.inbound.WalkUseCase;
import social.connectus.walk.domain.ports.outbound.WalkPort;

@UseCase
@RequiredArgsConstructor
public class WalkService implements WalkUseCase {
    private final WalkPort walkPort;
    @Override
    public CreateWalkResponse createWalk(CreateWalkCommand command) {
        /*
        TODO: 업적 갱신 요청 보내기
         */

        Walk walk = walkPort.createWalk(command);
        return CreateWalkResponse.from(walk);
    }
}
