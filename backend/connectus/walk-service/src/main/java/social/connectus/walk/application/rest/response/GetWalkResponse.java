package social.connectus.walk.application.rest.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.domain.model.VO.Position;
import social.connectus.walk.domain.model.entity.Walk;

import java.util.List;

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
    private int walkDistance;
    private List<Long> likeUsers;
    private List<Long> completedAchievement;
    private Long participateEvent;

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
                .build();
    }
}
