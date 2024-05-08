package social.connectus.userservice.domain.port.inbound;

import social.connectus.userservice.domain.application.response.CompletedAchievementListResponse;

public interface AchievementUseCase {
	public CompletedAchievementListResponse checkAchievement(Long userId);
}
