package social.connectus.userservice.domain.application.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import social.connectus.userservice.domain.model.entity.User;

@Getter
@Builder
@AllArgsConstructor
public class UserResponseForPost {
	String authorNickName;
	String avatarImageUrl;

	public static UserResponseForPost from(User user) {
		return UserResponseForPost.builder()
			.authorNickName(user.getNickname())
			.avatarImageUrl(user.getAvatarImageUrl())
			.build();
	}
}
