package social.connectus.userservice.domain.services;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import social.connectus.userservice.common.annotation.UseCase;
import social.connectus.userservice.common.type.Achievement;
import social.connectus.userservice.domain.application.response.AchievementResponse;
import social.connectus.userservice.domain.application.response.CompletedAchievementListResponse;
import social.connectus.userservice.domain.model.entity.User;
import social.connectus.userservice.domain.port.inbound.AchievementUseCase;
import social.connectus.userservice.domain.port.outbound.repository.UserRepository;

@UseCase
@RequiredArgsConstructor
public class AchievementService implements AchievementUseCase {
	private final UserRepository userRepository;

	@Override
	public CompletedAchievementListResponse checkAchievement(Long userId) {
		User user = userRepository.findById(userId).get();

		List<Achievement> accomplishedAchievement = user.getAccomplishedAchievements();
		List<Achievement> uncompletedAchievement = new ArrayList<>();
		for (Achievement achievement : Achievement.values()) {
			if (!accomplishedAchievement.contains(achievement)) {
				uncompletedAchievement.add(achievement);
			}
		}
		return CompletedAchievementListResponse.from(accomplishedAchievement, uncompletedAchievement);
	}
}
