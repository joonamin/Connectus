package social.connectus.userservice.domain.port.outbound;

import java.util.List;

import social.connectus.userservice.application.request.CreateUserPositionRequest;
import social.connectus.userservice.application.request.InsertPostRequest;
<<<<<<< HEAD
import social.connectus.userservice.application.response.LikeResponse;
=======
import social.connectus.userservice.application.response.ChangePositionResponse;
>>>>>>> backend
import social.connectus.userservice.application.response.PointResponse;
import social.connectus.userservice.application.response.UserInfoResponse;
import social.connectus.userservice.common.exception.FailedToLoginException;
import social.connectus.userservice.application.response.OpenedPostResponse;
import social.connectus.userservice.domain.command.PointChangeCommand;
import social.connectus.userservice.domain.model.entity.User;
import social.connectus.userservice.domain.port.inbound.command.*;
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

	ChangePositionResponse insertUserPosition(CreateUserPositionRequest request);

	ChangePositionResponse updateUserPosition(CreateUserPositionRequest request);

	void deleteUserPosition(Long userId);
	UserInfoResponse getUserInfo(Long userId);
	LikeResponse getMyLikeList(Long userId);
}
