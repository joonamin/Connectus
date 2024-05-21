package social.connectus.userservice.domain.port.inbound;

import java.util.List;

import social.connectus.userservice.application.request.RefreshAchievementRequest;
import social.connectus.userservice.application.response.AchievementResponse;
import social.connectus.userservice.application.response.CompletedAchievementListResponse;
import social.connectus.userservice.application.response.RefreshAchievementResponse;

public interface AchievementUseCase {
	public CompletedAchievementListResponse checkAchievement(Long userId);

	public RefreshAchievementResponse refreshAchievement(Long userId, RefreshAchievementRequest request);

	public List<AchievementResponse> getMyAchievements(Long userId);
}
