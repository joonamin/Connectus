package social.connectus.walk.application.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.domain.model.VO.Position;
import social.connectus.walk.domain.model.entity.*;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetWalkResponse {
    private Long walkId;
    private Long userId;
    private String title;
    private List<Route> route;
    private int walkTime;
    private double walkDistance;
    private Set<LikeUser> likeUsers;
    private Set<Achievement> completedAchievement;
    private Long participateEvent;
    private Set<TrackingUser> trackingUsers;
    private boolean isPublic;

    public static GetWalkResponse from(Walk walk){
        return GetWalkResponse.builder()
                .walkId(walk.getWalkId())
                .userId(walk.getUserId())
                .title(walk.getTitle())
                .route(walk.getRoute())
                .walkTime(walk.getWalkTime())
                .walkDistance(walk.getWalkDistance())
                .likeUsers(walk.getLikeUsers())
                .completedAchievement(walk.getCompletedAchievement())
                .participateEvent(walk.getParticipateEvent())
                .trackingUsers(walk.getTrackingUsers())
                .isPublic(walk.isPublic())
                .build();
    }
}
