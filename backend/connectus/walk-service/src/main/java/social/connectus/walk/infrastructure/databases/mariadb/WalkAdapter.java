package social.connectus.walk.infrastructure.databases.mariadb;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import social.connectus.walk.common.constants.WalkConstants;
import social.connectus.walk.common.exception.ResourceNotFoundException;
import social.connectus.walk.domain.command.*;
import social.connectus.walk.domain.model.entity.Walk;
import social.connectus.walk.domain.ports.outbound.WalkPort;
import social.connectus.walk.infrastructure.databases.mariadb.repository.WalkRepository;
import social.connectus.walk.infrastructure.external.FeignClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WalkAdapter implements WalkPort {
    private final FeignClient feignClient;

    private final ModelMapper modelMapper;
    private final WalkRepository walkRepository;

    public String feignHealthCheck(){
        return feignClient.healthCheck();
    }

    @Override
    public Walk getWalkById(long walkId) {
        return walkRepository.findById(walkId)
                .orElseThrow(()-> new ResourceNotFoundException(WalkConstants.WALK_NOT_FOUND + walkId));
    }

    @Override
    public List<Walk> getWalkByUser(long userId) {
        return walkRepository.findByUser(userId);
    }

    @Transactional
    @Override
    public void routeShare(RouteShareCommand command) {
        long walkId = command.getWalkId();
        Walk walk = getWalkById(walkId);
        walk.setPublic(true);
    }

    @Transactional
    @Override
    public void routeProtect(RouteProtectCommand command) {
        long walkId = command.getWalkId();
        Walk walk = getWalkById(walkId);
        walk.setPublic(false);
    }

    @Transactional
    @Override
    public void routeTrack(RouteTrackCommand command) {
        Walk walk = getWalkById(command.getWalkId());
        walk.getTrackingUsers().add(command.getUserId());
    }

    @Override
    @Transactional
    public Walk createWalk(CreateWalkCommand command) {
        Walk walk = Walk.builder()
                .userId(command.getUserId())
                .title(command.getTitle())
                .route(command.getRoute())
                .completedAchievement(command.getCompletedAchievement())
                .walkTime(command.getWalkTime())
                .walkDistance(command.getWalkDistance())
                .participateEvent(command.getParticipateEvent())
                .isPublic(command.isPublic())
                .build();

        return walkRepository.save(walk);
    }

    @Override
    @Transactional
    public void routeLike(RouteLikeCommand command) {
        long walkId = command.getWalkId();
        long userId = command.getUserId();
        Walk walk = getWalkById(walkId);

        walk.getLikeUsers().add(userId);
    }
}
