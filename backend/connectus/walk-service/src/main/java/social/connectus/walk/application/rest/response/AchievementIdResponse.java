package social.connectus.walk.application.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.domain.model.entity.CompletedAchievement;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AchievementIdResponse {
    private Long achievementId;
    private boolean isSucccess;
    public static AchievementIdResponse from(CompletedAchievement completedAchievement){
        return AchievementIdResponse.builder()
                .achievementId(completedAchievement.getAchievementId())
                .isSucccess(completedAchievement.isSuccess())
                .build();
    }
}
