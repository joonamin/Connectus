package social.connectus.walk.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.application.rest.request.GetAchievementsRequest;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAchievementsCommand {
    private Integer postCount;
    private Long participateEvent;
}
