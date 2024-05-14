package social.connectus.walk.infrastructure.databases.mariadb;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Component;
import social.connectus.walk.common.constants.WalkConstants;
import social.connectus.walk.common.exception.ResourceNotFoundException;
import social.connectus.walk.domain.command.*;
import social.connectus.walk.domain.model.VO.Position;
import social.connectus.walk.domain.model.entity.*;
import social.connectus.walk.domain.ports.outbound.WalkPort;
import social.connectus.walk.infrastructure.databases.mariadb.repository.LikeUserRepository;
import social.connectus.walk.infrastructure.databases.mariadb.repository.RouteRepository;
import social.connectus.walk.infrastructure.databases.mariadb.repository.WalkRepository;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class WalkAdapter implements WalkPort {
    private final ModelMapper modelMapper;
    private final WalkRepository walkRepository;
    private final RouteRepository routeRepository;
    private final LikeUserRepository likeUserRepository;

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

    @Override
    public Slice<Long> getWalksByPosition(GetWalksByPositionCommand command) {
        Position userPosition = Position.builder()
                .latitude(command.getLatitude())
                .longitude(command.getLongitude())
                .build();
        double kmRadius = command.getKmRadius();
        long userId = command.getUserId();
        PageRequest pageRequest = PageRequest.of(command.getPageNumber(), command.getPageSize());
        Slice<Route> routeList = routeRepository.findSliceByPosition(userPosition.getLatitude(), userPosition.getLongitude(), kmRadius, 111.2D, 89.85D, pageRequest);
        return new SliceImpl<>(routeList.getContent().stream().map(route -> route.getWalk().getId()).toList(), pageRequest, routeList.hasNext());
    }


    @Transactional
    @Override
    public void routeLikeCancle(RouteLikeCommand command) {
//        Walk walk = getWalkById(command.getWalkId());
//        for(LikeUser user : walk.getLikeUsers()){
//            if(user.getUserId() == command.getUserId()){
//                walk.getLikeUsers().remove(user);
//                break;
//            }
//        }
//        walkRepository.save(walk);
        List<LikeUser> user = likeUserRepository.findByUserIdAndWalkId(command.getUserId(), command.getWalkId());
        likeUserRepository.deleteAll(user);
    }

    @Transactional
    @Override
    public void routeTrack(RouteTrackCommand command) {
        Walk walk = getWalkById(command.getWalkId());
        walk.getTrackingUsers().add(TrackingUser.builder().userId(command.getUserId()).walk(walk).build());
    }

    @Override
    @Transactional
    public Walk createWalk(CreateWalkCommand command) {
        Walk walk = Walk.builder()
                .userId(command.getUserId())
                .title(command.getTitle())
                .route(command.getRoute())
                .walkDistance(command.getWalkDistance())
                .walkTime(command.getWalkTime())
                .completedAchievement(command.getCompletedAchievement())
                .participateEvent(command.getParticipateEvent())
                .isPublic(command.isPublic())
                .build();

        createRoute(walk.getRoute(), walk);
        createAchievement(walk.getCompletedAchievement(), walk);
        walkRepository.save(walk);
        return walk;
    }

    @Override
    public void createPostList(List<Post> postList, Walk walk) {
        postList.forEach(post -> post.setWalk(walk));
    }

    @Override
    public void createRoute(List<Route> routes, Walk walk){
        routes.forEach(route -> route.setWalk(walk));
    }

    @Override
    public void createAchievement(Set<CompletedAchievement> completedAchievements, Walk walk){
        completedAchievements.forEach(achievement -> achievement.setWalk(walk));
    }

    @Override
    @Transactional
    public void routeLike(RouteLikeCommand command) {
        long walkId = command.getWalkId();
        long userId = command.getUserId();
        Walk walk = getWalkById(walkId);

        walk.getLikeUsers().add(LikeUser.builder().userId(userId).walk(walk).build());
    }
}
