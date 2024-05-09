package social.connectus.walk.domain.command;

import lombok.*;
import social.connectus.walk.application.rest.request.CreateWalkRequest;
import social.connectus.walk.domain.model.VO.Position;
import social.connectus.walk.domain.model.entity.Achievement;
import social.connectus.walk.domain.model.entity.Route;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateWalkCommand {
    private Long userId;
    private String title;
    private List<Route> route;
    private Set<Achievement> completedAchievement;
    private int walkTime;
    private double walkDistance;   // 산책 거리
    private Long participateEvent;
    private boolean isPublic;

    public static CreateWalkCommand from(CreateWalkRequest request){
        return CreateWalkCommand.builder()
                .userId(request.getUserId())
                .title(request.getTitle())
                .route(request.getRoute())
                .completedAchievement(request.getCompletedAchievement())
                .walkTime(request.getWalkTime())
                .walkDistance(request.getWalkDistance())
                .participateEvent(request.getParticipateEvent())
                .isPublic(request.isPublic())
                .build();
    }

}
