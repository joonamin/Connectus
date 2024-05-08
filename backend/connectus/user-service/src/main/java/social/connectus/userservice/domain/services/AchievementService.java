package social.connectus.userservice.domain.services;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import social.connectus.userservice.common.annotation.UseCase;
import social.connectus.userservice.common.type.Achievement;
import social.connectus.userservice.domain.application.request.RefreshAchievementRequest;
import social.connectus.userservice.domain.application.response.AchievementResponse;
import social.connectus.userservice.domain.application.response.CompletedAchievementListResponse;
import social.connectus.userservice.domain.application.response.RefreshAchievementResponse;
import social.connectus.userservice.domain.port.inbound.AchievementUseCase;
import social.connectus.userservice.domain.port.outbound.AchievementPort;
import social.connectus.userservice.domain.port.outbound.command.RefreshAchievementToUserCommand;
import social.connectus.userservice.domain.port.outbound.command.UserToRefreshAchievementCommand;

@UseCase
@RequiredArgsConstructor
public class AchievementService implements AchievementUseCase {
	private final AchievementPort achievementPort;

	@Override
	public CompletedAchievementListResponse checkAchievement(Long userId) {
		List<Achievement> accomplishedAchievement = achievementPort.completedAchievement(userId);
		List<Achievement> uncompletedAchievement = new ArrayList<>();
		for (Achievement achievement : Achievement.values()) {
			if (!accomplishedAchievement.contains(achievement)) {
				uncompletedAchievement.add(achievement);
			}
		}
		return CompletedAchievementListResponse.from(accomplishedAchievement, uncompletedAchievement);
	}

	@Override
	public RefreshAchievementResponse refreshAchievement(Long userId, RefreshAchievementRequest request) {
		UserToRefreshAchievementCommand userData = achievementPort.refreshAchievement(userId);

		RefreshAchievementToUserCommand sumData = RefreshAchievementToUserCommand.from(userData, request);
		List<Achievement> accomplishedAchievement = new ArrayList<>();

		for (Achievement achievement : Achievement.values()) {
			int progress = achievement.getField().getGetter().apply(sumData);
			float goal = achievement.getGoal();

			if (progress >= goal)
				accomplishedAchievement.add(achievement);
		}
		sumData.setAccomplishedAchievement(accomplishedAchievement);
		achievementPort.updateAchievementData(userId, sumData);
		accomplishedAchievement.removeAll(userData.getAccomplishedAchievement());

		return new RefreshAchievementResponse(accomplishedAchievement.stream().map(AchievementResponse::from).toList());
	}

}
