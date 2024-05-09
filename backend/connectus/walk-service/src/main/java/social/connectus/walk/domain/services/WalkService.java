package social.connectus.walk.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import social.connectus.walk.application.rest.response.CreateWalkResponse;
import social.connectus.walk.common.customannotations.UseCase;
import social.connectus.walk.common.exception.AlreadyExistsDataException;
import social.connectus.walk.domain.command.*;
import social.connectus.walk.domain.model.entity.Walk;
import social.connectus.walk.domain.ports.inbound.WalkUseCase;
import social.connectus.walk.domain.ports.outbound.WalkPort;

import java.util.List;

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
    public List<Walk> getWalkByUser(long userId) {
        return walkPort.getWalkByUser(userId);
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
        Walk walk = walkPort.getWalkById(command.getWalkId());
        if(walk.getLikeUsers().contains(command.getUserId()))
            throw new AlreadyExistsDataException("User already like this walk.");

        walkPort.routeLike(command);
    }

    @Override
    public void routeShare(RouteShareCommand command) {
        Walk walk = walkPort.getWalkById(command.getWalkId());
        if(walk.isPublic())
            throw new AlreadyExistsDataException("User already share this walk.");

        walkPort.routeShare(command);
    }

    @Override
    public void routeTrack(RouteTrackCommand command) {
        Walk walk = walkPort.getWalkById(command.getWalkId());
        if(walk.getTrackingUsers().contains(command.getUserId()))
            throw new AlreadyExistsDataException("User already track this walk.");

        walkPort.routeTrack(command);
    }

    @Override
    public void routeProtect(RouteProtectCommand command) {
        Walk walk = walkPort.getWalkById(command.getWalkId());
        if(walk.isPublic())
            throw new AlreadyExistsDataException("User already protect this walk.");

        walkPort.routeProtect(command);
    }

    @Override
    public Slice<Long> getWalksByPosition(GetWalksByPositionCommand command) {
        return walkPort.getWalksByPosition(command);
    }
}
