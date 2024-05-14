package social.connectus.userservice.domain.port.external.port;

import java.util.List;

import social.connectus.userservice.common.type.Achievement;
import social.connectus.userservice.domain.port.client.response.AchievementResponse;
import social.connectus.userservice.domain.port.outbound.command.RefreshAchievementToUserCommand;
import social.connectus.userservice.domain.port.outbound.command.UserToRefreshAchievementCommand;

public interface AchievementPort {

	List<Achievement> completedAchievement(Long userId);

	UserToRefreshAchievementCommand refreshAchievement(Long userId);

	void updateAchievementData(Long userId, RefreshAchievementToUserCommand command);

	List<AchievementResponse> getMyAchievements(Long userId);
}
