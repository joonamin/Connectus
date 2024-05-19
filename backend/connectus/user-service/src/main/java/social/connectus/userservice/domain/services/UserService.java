package social.connectus.userservice.domain.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import social.connectus.userservice.application.request.InsertPostRequest;
import social.connectus.userservice.application.request.CreateUserPositionRequest;
import social.connectus.userservice.application.request.UpdateAvatarRequest;
import social.connectus.userservice.application.response.*;
import social.connectus.userservice.common.annotation.UseCase;
import social.connectus.userservice.common.aop.annotation.YetNotImplemented;
import social.connectus.userservice.common.exception.FailedToLoginException;
import social.connectus.userservice.common.exception.FailedToLogoutException;
import social.connectus.userservice.common.exception.FailedToRegisterUserException;
import social.connectus.userservice.common.type.JwtPayload;
import social.connectus.userservice.common.type.JwtPropertiesProvider;
import social.connectus.userservice.common.utils.JwtProvider;
import social.connectus.userservice.domain.command.PointChangeCommand;
import social.connectus.userservice.application.response.LoginUserResponse;
import social.connectus.userservice.application.response.LogoutUserResponse;
import social.connectus.userservice.application.response.OpenedPostResponse;
import social.connectus.userservice.domain.model.entity.User;
import social.connectus.userservice.domain.port.inbound.UserUseCase;
import social.connectus.userservice.domain.port.inbound.command.*;
import social.connectus.userservice.domain.port.outbound.UserPort;

@RequiredArgsConstructor
@Component
@UseCase
public class UserService implements UserUseCase {

	private final UserPort userPort;
	private final JwtProvider jwtProvider;

	@Override
	public void register(UserRegisterCommand command) throws FailedToRegisterUserException {
		userPort.registerUser(command);
	}

	@Override
	public LoginUserResponse login(UserLoginCommand command) throws FailedToLoginException {
		User user = Optional.ofNullable(userPort.loginUser(command)).orElseThrow(() -> new FailedToLoginException(
			"유저가 존재하지 않습니다"));

		String accessToken = jwtProvider.createAccessToken(JwtPayload.builder()
			.issuedAt(new Date())
			.issuer(JwtPropertiesProvider.ISSUER.getValue())
			.email(user.getEmail())
			.userId(user.getUserId())
			.build());

		return LoginUserResponse.builder().accessToken(accessToken).build();
	}

	@Override
	public void updateOpenedPost(Long userId, Long postId) {
		userPort.updateOpenedPost(userId, postId);
	}
	@Override
	public void updateOpenedPosts(Long userId, List<Long> postIdList) {
		userPort.updateOpenedPosts(userId,postIdList);
	}
	@Override
	public OpenedPostResponse getOpenedPost(Long userId) {
		return userPort.getOpenedPost(userId);
	}

	@Override
	public UserResponseForPost getUserResponseForPost(Long userId) {
		return userPort.getUserResponseForPost(userId);
	}

	@Override
	@YetNotImplemented
	public LogoutUserResponse logout(UserLogoutCommand command) throws FailedToLogoutException {
		return null;
	}

	@Override
	public PointResponse increasePoint(PointChangeCommand command) {
		return userPort.increasePoint(command);
	}

	@Override
	public PointResponse decreasePoint(PointChangeCommand command) {
		return userPort.decreasePoint(command);
	}

	@Override
	public String updateAvatar(Long userId, UpdateAvatarRequest request) {
		return userPort.updateAvatar(userId, request);
	}

	@Override
	public ChangePositionResponse insertUserPosition(CreateUserPositionRequest request) {
		return userPort.insertUserPosition(request);
	}

	@Override
	public ChangePositionResponse updateUserPosition(CreateUserPositionRequest request) {
		return userPort.updateUserPosition(request);
	}

	@Override
	public void deleteUserPosition(Long userId) {
		userPort.deleteUserPosition(userId);
	}

	@Override
	public UserInfoResponse getUserInfo(Long userId) {
		return userPort.getUserInfo(userId);
	}

	@Override
	public PointResponse insertPostHistory(InsertPostRequest request) {
		PointResponse result = userPort.decreasePoint(new PointChangeCommand(request.getUserId(), 1));
		userPort.updateOpenedPost(request.getUserId(), request.getPostId());
		return result;
	}


}
