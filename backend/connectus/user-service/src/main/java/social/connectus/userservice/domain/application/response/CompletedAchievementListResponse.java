package social.connectus.userservice.domain.application.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import social.connectus.userservice.common.type.Achievement;

@Getter
@AllArgsConstructor
@Builder
public class CompletedAchievementListResponse {
	List<AchievementResponse> completedAchievement;
	List<AchievementResponse> uncompletedAchievement;

	public static CompletedAchievementListResponse from(List<Achievement> completedAchievement,
		List<Achievement> uncompletedAchievement) {
		return CompletedAchievementListResponse.builder()
			.completedAchievement(completedAchievement.stream().map(AchievementResponse::from).toList())
			.uncompletedAchievement(uncompletedAchievement.stream().map(AchievementResponse::from).toList())
			.build();
	}
}
