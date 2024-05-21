package social.connectus.userservice.domain.services;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import social.connectus.userservice.common.annotation.UseCase;
import social.connectus.userservice.common.type.Achievement;
import social.connectus.userservice.application.request.RefreshAchievementRequest;
import social.connectus.userservice.application.response.AchievementResponse;
import social.connectus.userservice.application.response.CompletedAchievementListResponse;
import social.connectus.userservice.application.response.RefreshAchievementResponse;
import social.connectus.userservice.domain.model.entity.User;
import social.connectus.userservice.domain.port.external.port.AchievementPort;
import social.connectus.userservice.domain.port.inbound.AchievementUseCase;
import social.connectus.userservice.domain.port.outbound.UserPort;
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
		List<Achievement> accomplishedAchievement = new ArrayList<>();  // 700 post , walk + 1

		for (Achievement achievement : Achievement.values()) {
			int progress = achievement.getField().getGetter().apply(sumData);
			float goal = achievement.getGoal();

			if (progress >= goal)
				accomplishedAchievement.add(achievement);
		}
		List<Achievement> achievementList= userData.getAccomplishedAchievement(); // 500 post, walk
		accomplishedAchievement.removeAll(achievementList);
		sumData.setAccomplishedAchievement(accomplishedAchievement);
		achievementPort.updateAchievementData(userId, sumData);

		return new RefreshAchievementResponse(accomplishedAchievement.stream().map(AchievementResponse::from).toList());
	}

	@Override
	public List<AchievementResponse> getMyAchievements(Long userId) {
		var myAchievements = achievementPort.getMyAchievements(
			userId);
		return myAchievements.stream().map(item -> {
			return AchievementResponse.builder()
				.title(item.getTitle())
				.reward(item.getReward())
				.content(item.getTitle())
				.build();
		}).toList();
	}

}
