package social.connectus.userservice.domain.services;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import social.connectus.userservice.common.aop.annotation.YetNotImplemented;
import social.connectus.userservice.common.exception.FailedToRegisterUserException;
import social.connectus.userservice.domain.application.response.LoginUserResponse;
import social.connectus.userservice.domain.application.response.LogoutUserResponse;
import social.connectus.userservice.domain.application.response.OpenedPostResponse;
import social.connectus.userservice.domain.port.inbound.command.UserLoginCommand;
import social.connectus.userservice.domain.port.inbound.command.UserLogoutCommand;
import social.connectus.userservice.domain.port.inbound.command.UserRegisterCommand;
import social.connectus.userservice.domain.port.inbound.UserUseCase;
import social.connectus.userservice.domain.port.outbound.UserPort;

@RequiredArgsConstructor
@Component
public class UserService implements UserUseCase {

	private final UserPort userPort;

	@Override
	public void register(UserRegisterCommand command) throws FailedToRegisterUserException {
		userPort.registerUser(command);
	}

	@Override
	public LoginUserResponse login(UserLoginCommand command) throws RuntimeException {
		return null;
	}

	@Override
	@YetNotImplemented
	public LogoutUserResponse logout(UserLogoutCommand command) throws RuntimeException {
		return null;
	}

	@Override
	public void updateOpenedPosts(Long userId, Long postId) {
		userPort.updateOpenedPosts(userId, postId);
	}

	@Override
	public OpenedPostResponse getOpenedPost(Long userId) {
		return userPort.getOpenedPost(userId);
	}

}
