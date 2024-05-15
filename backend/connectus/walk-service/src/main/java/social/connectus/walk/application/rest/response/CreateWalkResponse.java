package social.connectus.walk.application.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.domain.model.entity.Walk;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateWalkResponse {
    private long walkId;
    private List<AchievementResponse> completedAchievement;
    public static CreateWalkResponse from(Walk walk){
        return CreateWalkResponse.builder()
                .walkId(walk.getId())
                .build();
    }
}
