package social.connectus.walk.domain.command;

import lombok.*;
import social.connectus.walk.domain.model.VO.Position;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateWalkCommand {
    private Long userId;
    private String title;
    private List<Position> route;
    private List<Long> completedAchievement;
    private int walkTime;
    private int walkDistance;   // 산책 거리
    private Long participateEvent;

}
