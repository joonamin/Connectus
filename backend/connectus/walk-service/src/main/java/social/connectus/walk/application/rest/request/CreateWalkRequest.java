package social.connectus.walk.application.rest.request;

import lombok.*;
import social.connectus.walk.domain.model.VO.Route;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateWalkRequest {
    private Long userId;
    private String title;
    private Route route;
    private List<Long> completedAchievement;
    private int walkTime;
    private int walkDistance;   // 산책 거리
    private Long participateEvent;
}
