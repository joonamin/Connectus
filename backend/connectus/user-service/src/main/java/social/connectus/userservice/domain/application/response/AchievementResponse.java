package social.connectus.userservice.domain.application.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import social.connectus.userservice.common.type.Achievement;

@Getter
@Builder
@AllArgsConstructor
public class AchievementResponse {
	String title;
	String content;
	int reward;

	public static AchievementResponse from(Achievement achievement) {
		return AchievementResponse.builder()
			.title(achievement.getTitle())
			.content(achievement.getContent())
			.reward(achievement.getReward())
			.build();
	}
}
