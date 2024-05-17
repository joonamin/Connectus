package social.connectus.userservice.domain.application.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import social.connectus.userservice.common.type.Achievement;

@Getter
@Builder
@AllArgsConstructor
public class AchievementResponse {
	String achievementCode;
	String title;
	String content;
	String imageUrl;
	int reward;

	public static AchievementResponse from(Achievement achievement) {
		return AchievementResponse.builder()
			.achievementCode(achievement.toString())
			.title(achievement.getTitle())
			.content(achievement.getContent())
			.reward(achievement.getReward())
			.imageUrl(achievement.getImageUrl())
			.build();
	}
}
