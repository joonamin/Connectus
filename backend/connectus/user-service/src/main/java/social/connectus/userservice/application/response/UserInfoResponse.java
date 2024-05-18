package social.connectus.userservice.application.response;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.userservice.common.type.Achievement;
import social.connectus.userservice.domain.model.entity.User;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
	private Long userId;
	private int point;
	private int postCount;
	private String nickname;
	private int completedAchieveCount;
	private String imageUrl;

	public static UserInfoResponse from(User user) {
		return UserInfoResponse.builder()
			.userId(user.getUserId())
			.nickname(user.getNickname())
			.postCount(user.getPostCount())
			.completedAchieveCount(user.getAccomplishedAchievements().size())
			.imageUrl(user.getAvatarImageUrl())
			.build();
	}
}
