package social.connectus.userservice.domain.port.external.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import social.connectus.userservice.common.exception.NotFoundException;
import social.connectus.userservice.common.type.Achievement;
import social.connectus.userservice.domain.model.entity.User;
import social.connectus.userservice.domain.port.client.AchievementClient;
import social.connectus.userservice.domain.port.client.response.AchievementResponse;
import social.connectus.userservice.domain.port.external.port.AchievementPort;
import social.connectus.userservice.domain.port.outbound.command.RefreshAchievementToUserCommand;
import social.connectus.userservice.domain.port.outbound.command.UserToRefreshAchievementCommand;
import social.connectus.userservice.domain.port.outbound.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class AchievementAdapter implements AchievementPort {

	private final UserRepository userRepository;
	private final AchievementClient achievementClient;

	@Override
	public List<Achievement> completedAchievement(Long userId) {
		RuntimeException NotFoundException;
		return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user doesn't exists"))
			.getAccomplishedAchievements();
	}

	@Override
	public UserToRefreshAchievementCommand refreshAchievement(Long userId) {
		return UserToRefreshAchievementCommand.from(
			userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user doesn't exists"))
		);
	}

	@Override
	public void updateAchievementData(Long userId, RefreshAchievementToUserCommand command) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user doesn't exists"));
		user.updateAchievement(command);
		userRepository.save(user);
	}

	@Override
	public List<AchievementResponse> getMyAchievements(Long userId) {
		return achievementClient.getUserAchievements(userId);
	}

}
