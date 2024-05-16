package social.connectus.userservice.domain.port.outbound;

import social.connectus.userservice.common.exception.FailedToLoginException;
import social.connectus.userservice.domain.application.response.OpenedPostResponse;
import social.connectus.userservice.domain.model.entity.User;
import social.connectus.userservice.domain.port.inbound.command.UserLoginCommand;
import social.connectus.userservice.domain.port.inbound.command.UserLogoutCommand;
import social.connectus.userservice.domain.port.inbound.command.UserRegisterCommand;

public interface UserPort {

	void registerUser(UserRegisterCommand command);

	User loginUser(UserLoginCommand command) throws FailedToLoginException;

	void logoutUser(UserLogoutCommand command);

	void updateOpenedPosts(Long userId, Long postId);
	OpenedPostResponse getOpenedPost(Long userId);
	String getUserNickname(Long userId);
}
