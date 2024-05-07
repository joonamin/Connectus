package social.connectus.walk.application.rest.request;

import lombok.*;
import social.connectus.walk.domain.model.VO.Position;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateWalkRequest {
    private Long userId;
    private String title;
    private List<Position> route;
    private List<Long> completedAchievement;
    private int walkTime;
    private int walkDistance;   // 산책 거리
    private Long participateEvent;
}
