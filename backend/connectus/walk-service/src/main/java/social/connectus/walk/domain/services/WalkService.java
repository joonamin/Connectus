package social.connectus.walk.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import social.connectus.walk.application.rest.request.CreatePostRequest;
import social.connectus.walk.application.rest.request.PostRequestForWalk;
import social.connectus.walk.application.rest.response.AchievementResponse;
import social.connectus.walk.application.rest.response.CreateWalkResponse;
import social.connectus.walk.common.customannotations.UseCase;
import social.connectus.walk.common.exception.AlreadyExistsDataException;
import social.connectus.walk.domain.command.*;
import social.connectus.walk.domain.model.VO.PostVO;
import social.connectus.walk.domain.model.entity.Post;
import social.connectus.walk.domain.model.entity.Walk;
import social.connectus.walk.domain.ports.inbound.WalkUseCase;
import social.connectus.walk.domain.ports.outbound.FeignPort;
import social.connectus.walk.domain.ports.outbound.ImagePort;
import social.connectus.walk.domain.ports.outbound.WalkPort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class WalkService implements WalkUseCase {

    private final WalkPort walkPort;
    private final ImagePort imagePort;
    private final FeignPort feignPort;

    @Override
    public Walk getWalkById(long walkId){
        return walkPort.getWalkById(walkId);
    }

    @Override
    public List<Walk> getWalkByUser(long userId) {
        return walkPort.getWalkByUser(userId);
    }

    @Override
    public CreateWalkResponse createWalk(CreateWalkCommand command) throws IOException {
        /*
        TODO: 업적 갱신 요청 보내기
         */

        String imageUrl = null;
        if(command.getImage() != null)
            imageUrl = imagePort.uploadImage(command.getImage());

        Walk walk = Walk.builder()
                .userId(command.getUserId())
                .title(command.getTitle())
                .route(command.getRoute())
                .walkDistance(command.getWalkDistance())
                .walkTime(command.getWalkTime())
                .completedAchievement(command.getCompletedAchievement())
                .participateEvent(command.getParticipateEvent())
                .isPublic(command.isPublic())
                .imageUrl(imageUrl)
                .build();
        walkPort.createWalk(walk);
        long walkId = walk.getId();

        List<PostVO> postVOList = new ArrayList<>();
        for(PostRequestForWalk postReq : command.getPostList()){
            PostVO postVO = PostVO.builder()
                    .content(postReq.getContent())
                    .image(postReq.getImage())
                    .authorId(postReq.getAuthorId())
                    .walkId(walkId)
                    .build();
            postVOList.add(postVO);
        }
        List<Long> postIdList = feignPort.createPost(CreatePostRequest.builder()
                        .walkId(walkId)
                        .postList(postVOList)
                .build());
        List<Post> postList = new ArrayList<>();
        for(Long postId : postIdList){
            Post post = new Post(postId);
            postList.add(post);
        }
        walk.setPostList(postList);

        walkPort.createPostList(postList, walk);

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
    public Slice<Long> getWalkIdsByPosition(GetWalksByPositionCommand command) {
        return walkPort.getWalkIdsByPosition(command);
    }

    @Override
    public double getDistance(double latStart, double lonStart, double latEnd, double lonEnd) {
        final int EARTH_RADIUS = 6371;
        double dLat = Math.toRadians(latEnd - latStart);
        double dLon = Math.toRadians(lonEnd - lonStart);

        double a = Math.sin(dLat/2)* Math.sin(dLat/2)+ Math.cos(Math.toRadians(latStart))* Math.cos(Math.toRadians(latEnd))* Math.sin(dLon/2)* Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = EARTH_RADIUS* c * 1000;    // Distance in m
        return d;
    }

    @Override
    public List<AchievementResponse> getAchievementsByWalk(Long userId, GetAchievementsCommand command) {
        return feignPort.getAchievementsByWalk(userId, command);
    }

    @Override
    public Slice<Walk> getWalksByPosition(GetWalksByPositionCommand command) {
        return walkPort.getWalksByPosition(command);
    }
}
