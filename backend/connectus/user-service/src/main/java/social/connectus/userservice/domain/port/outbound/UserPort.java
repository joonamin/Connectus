package social.connectus.userservice.domain.port.outbound;

import java.util.List;

import social.connectus.userservice.common.exception.FailedToLoginException;
import social.connectus.userservice.domain.application.request.UserPositionRequest;
import social.connectus.userservice.domain.application.response.OpenedPostResponse;
import social.connectus.userservice.domain.application.response.UserResponseForPost;
import social.connectus.userservice.domain.model.entity.User;
import social.connectus.userservice.domain.port.inbound.command.UserLoginCommand;
import social.connectus.userservice.domain.port.inbound.command.UserLogoutCommand;
import social.connectus.userservice.domain.port.inbound.command.UserPositionCommand;
import social.connectus.userservice.domain.port.inbound.command.UserRegisterCommand;

public interface UserPort {

	void registerUser(UserRegisterCommand command);

	User loginUser(UserLoginCommand command) throws FailedToLoginException;

	void logoutUser(UserLogoutCommand command);

	void updateOpenedPost(Long userId, Long postId);
	void updateOpenedPosts(Long userId, List<Long> postIdList);
	OpenedPostResponse getOpenedPost(Long userId);
	String updateAvatar(Long userId, String imageUrl);
	UserResponseForPost getUserResponseForPost(Long userId);
	void insertUserPosition(List<UserPositionCommand> userPositionCommand);
}
