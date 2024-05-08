package social.connectus.walk.domain.services;

import lombok.RequiredArgsConstructor;
import social.connectus.walk.application.rest.response.CreateWalkResponse;
import social.connectus.walk.common.customannotations.UseCase;
import social.connectus.walk.common.exception.AlreadyExistsDataException;
import social.connectus.walk.domain.command.CreateWalkCommand;
import social.connectus.walk.domain.command.RouteLikeCommand;
import social.connectus.walk.domain.model.entity.Walk;
import social.connectus.walk.domain.ports.inbound.WalkUseCase;
import social.connectus.walk.domain.ports.outbound.WalkPort;

@UseCase
@RequiredArgsConstructor
public class WalkService implements WalkUseCase {

    private final WalkPort walkPort;

    @Override
    public String feignHealthCheck() {
        return walkPort.feignHealthCheck();
    }

    @Override
    public Walk getWalkById(long walkId){
        return walkPort.getWalkById(walkId);
    }

    @Override
    public CreateWalkResponse createWalk(CreateWalkCommand command) {
        /*
        TODO: 업적 갱신 요청 보내기
         */

        Walk walk = walkPort.createWalk(command);
        return CreateWalkResponse.from(walk);
    }

    public void routeLike(RouteLikeCommand command){
        // 이미 좋아요했는지 체크
        Walk walk = walkPort.getWalkById(command.getWalkId());
        if(walk.getLikeUsers().contains(command.getUserId()))
            throw new AlreadyExistsDataException("User already like this walk.");

        walkPort.routeLike(command);
    }
}
