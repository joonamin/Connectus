package social.connectus.walk.application.rest.request;

import lombok.*;
import social.connectus.walk.domain.model.VO.PostOnWalk;
import social.connectus.walk.domain.model.VO.Route;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateWalkRequest {
    private Long userId;
    private String title;
    private Route route;
    private List<Long> completedAchievement;
    private int walkTime;
    private int walkDistance;   // 산책 거
    private Long participateEvent;
    private List<PostOnWalk> postList;
}
