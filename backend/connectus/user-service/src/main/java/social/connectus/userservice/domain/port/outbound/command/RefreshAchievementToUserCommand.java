package social.connectus.userservice.domain.port.outbound.command;

import java.util.List;
import java.util.function.Function;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import social.connectus.userservice.common.type.Achievement;
import social.connectus.userservice.application.request.RefreshAchievementRequest;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RefreshAchievementToUserCommand {
	int walkCount;
	int postCount;
	// List<Long> completedEventIdList;
	List<Achievement> accomplishedAchievement;

	@Getter
	@AllArgsConstructor
	public enum Field {
		WALK_COUNT(RefreshAchievementToUserCommand::getWalkCount),
		POST_COUNT(RefreshAchievementToUserCommand::getPostCount);
		// COMPLETED_ACHIEVEMENT(RefreshAchievementToUserCommand::getCompletedEventIdList);
		private Function<RefreshAchievementToUserCommand, Integer> getter;
	}

	public static RefreshAchievementToUserCommand from(UserToRefreshAchievementCommand command,
		RefreshAchievementRequest request) {
		return RefreshAchievementToUserCommand.builder()
			.postCount(command.postCount + request.getPostCount())
			.walkCount(command.walkCount + 1)
			.build();
	}
}
