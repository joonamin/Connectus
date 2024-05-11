package social.connectus.userservice.domain.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import social.connectus.userservice.common.annotation.UseCase;
import social.connectus.userservice.common.aop.annotation.YetNotImplemented;
import social.connectus.userservice.common.exception.FailedToLoginException;
import social.connectus.userservice.common.exception.FailedToLogoutException;
import social.connectus.userservice.common.exception.FailedToRegisterUserException;
import social.connectus.userservice.common.type.JwtPayload;
import social.connectus.userservice.common.type.JwtPropertiesProvider;
import social.connectus.userservice.common.utils.JwtProvider;
import social.connectus.userservice.domain.application.response.LoginUserResponse;
import social.connectus.userservice.domain.application.response.LogoutUserResponse;
import social.connectus.userservice.domain.application.response.OpenedPostResponse;
import social.connectus.userservice.domain.model.entity.User;
import social.connectus.userservice.domain.port.inbound.UserUseCase;
import social.connectus.userservice.domain.port.inbound.command.UserLoginCommand;
import social.connectus.userservice.domain.port.inbound.command.UserLogoutCommand;
import social.connectus.userservice.domain.port.inbound.command.UserRegisterCommand;
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
		User user = Optional.ofNullable(userPort.loginUser(command)).orElseThrow(() -> {
			throw new FailedToLoginException("유저가 존재하지 않습니다");
		});

		String accessToken = jwtProvider.createAccessToken(JwtPayload.builder()
			.issuedAt(new Date())
			.issuer(JwtPropertiesProvider.ISSUER.getValue())
			.email(user.getEmail()).build());

		return LoginUserResponse.builder().accessToken(accessToken).build();
	}

	@Override
	public void updateOpenedPosts(Long userId, Long postId) {
		userPort.updateOpenedPosts(userId, postId);
	}

	@Override
	public OpenedPostResponse getOpenedPost(Long userId) {
		return userPort.getOpenedPost(userId);
	}

	@Override
	@YetNotImplemented
	public LogoutUserResponse logout(UserLogoutCommand command) throws FailedToLogoutException {
		return null;
	}

}
