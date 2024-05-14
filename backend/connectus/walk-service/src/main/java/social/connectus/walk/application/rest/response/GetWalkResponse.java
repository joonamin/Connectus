package social.connectus.walk.application.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.domain.model.VO.Position;
import social.connectus.walk.domain.model.entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetWalkResponse {
    private Long walkId;
    private Long userId;
    private String title;
    private List<Position> route;
    private int walkTime;
    private double walkDistance;
    private Set<Long> likeUsers;
    private List<Long> postList;
    private Set<AchievementIdResponse> completedAchievement;
    private Long participateEvent;
    private Set<Long> trackingUsers;
    private boolean isPublic;
    private LocalDateTime updatedAt;

    // model mapper 를 사용해서, 공통 필드는 자동으로 초기화 되게끔?
    // 할 수가 있어요
    // 추가적으로 task를 실행해서 초기화해야하는 필드는 직접 builder로 작성해주시는데..

    public static GetWalkResponse from(Walk walk){
        List<Position> positions = new ArrayList<>();

        walk.getRoute().forEach(route -> {
            positions.add(Position.builder()
                    .latitude(route.getLatitude())
                    .longitude(route.getLongitude())
                    .build());
        });
        Set<Long> likeUsers = walk.getLikeUsers()
                .stream()
                .map(LikeUser::getUserId)
                .collect(Collectors.toSet());
        Set<AchievementIdResponse> completedAchievement = walk.getCompletedAchievement()
                .stream()
                .map(AchievementIdResponse::from)
                .collect(Collectors.toSet());
        Set<Long> trackingUsers = walk.getTrackingUsers()
                .stream()
                .map(TrackingUser::getUserId)
                .collect(Collectors.toSet());
        List<Long> posts = walk.getPostList()
                .stream()
                .map(Post::getPostId)
                .toList();

        return GetWalkResponse.builder()
                .walkId(walk.getId())
                .userId(walk.getUserId())
                .title(walk.getTitle())
                .route(positions)
                .walkTime(walk.getWalkTime())
                .walkDistance(walk.getWalkDistance())
                .likeUsers(likeUsers)
                .postList(posts)
                .completedAchievement(completedAchievement)
                .participateEvent(walk.getParticipateEvent())
                .trackingUsers(trackingUsers)
                .isPublic(walk.isPublic())
                .updatedAt(walk.getUpdatedAt())
                .build();
    }
}
