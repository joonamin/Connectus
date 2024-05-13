package social.connectus.userservice.domain.port.outbound.command;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import social.connectus.userservice.common.type.Achievement;
import social.connectus.userservice.domain.model.entity.User;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserToRefreshAchievementCommand {
	List<Achievement> accomplishedAchievement;
	int postCount;
	int walkCount;

	public static UserToRefreshAchievementCommand from(User user) {
		return UserToRefreshAchievementCommand.builder()
			.accomplishedAchievement(user.getAccomplishedAchievements())
			.postCount(user.getPostCount())
			.walkCount(user.getWalkCount())
			.build();
	}
}
