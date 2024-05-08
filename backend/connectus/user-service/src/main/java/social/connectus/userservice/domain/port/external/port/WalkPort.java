package social.connectus.userservice.domain.port.external.port;

import java.util.List;

import social.connectus.userservice.domain.port.client.response.AchievementResponse;

public interface WalkPort {
	List<AchievementResponse> getMyAchievements(Long userId);
}
