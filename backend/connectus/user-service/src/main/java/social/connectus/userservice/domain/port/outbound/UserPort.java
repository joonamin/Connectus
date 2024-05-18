package social.connectus.userservice.domain.port.outbound;

import java.util.List;

import social.connectus.userservice.application.request.InsertPostRequest;
import social.connectus.userservice.application.response.PointResponse;
import social.connectus.userservice.application.response.UserInfoResponse;
import social.connectus.userservice.common.exception.FailedToLoginException;
import social.connectus.userservice.application.response.OpenedPostResponse;
import social.connectus.userservice.domain.command.PointChangeCommand;
import social.connectus.userservice.domain.model.entity.User;
import social.connectus.userservice.domain.port.inbound.command.UserLoginCommand;
import social.connectus.userservice.domain.port.inbound.command.UserLogoutCommand;
import social.connectus.userservice.domain.port.inbound.command.UserPositionCommand;
import social.connectus.userservice.domain.port.inbound.command.UserRegisterCommand;
import social.connectus.userservice.application.response.UserResponseForPost;

public interface UserPort {

	void registerUser(UserRegisterCommand command);

	User loginUser(UserLoginCommand command) throws FailedToLoginException;

	void logoutUser(UserLogoutCommand command);

	void updateOpenedPost(Long userId, Long postId);
	void updateOpenedPosts(Long userId, List<Long> postIdList);
	OpenedPostResponse getOpenedPost(Long userId);
	String getUserNickname(Long userId);

	PointResponse increasePoint(PointChangeCommand command);

	PointResponse decreasePoint(PointChangeCommand command);
	String updateAvatar(Long userId, String imageUrl);
	UserResponseForPost getUserResponseForPost(Long userId);
	String insertPostHistory(InsertPostRequest request);
	void insertUserPosition(UserPositionCommand userPositionCommand);
	UserInfoResponse getUserInfo(Long userId);
}
