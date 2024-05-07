package social.connectus.walk.domain.command;

import lombok.*;
import social.connectus.walk.application.rest.request.CreateWalkRequest;
import social.connectus.walk.domain.model.VO.Route;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateWalkCommand {
    private Long userId;
    private String title;
    private Route route;
    private List<Long> completedAchievement;
    private int walkTime;
    private int walkDistance;   // 산책 거리
    private Long participateEvent;

    public static CreateWalkCommand from(CreateWalkRequest request){
        return CreateWalkCommand.builder()
                .userId(request.getUserId())
                .title(request.getTitle())
                .route(request.getRoute())
                .completedAchievement(request.getCompletedAchievement())
                .walkTime(request.getWalkTime())
                .walkDistance(request.getWalkDistance())
                .participateEvent(request.getParticipateEvent())
                .build();
    }
}
