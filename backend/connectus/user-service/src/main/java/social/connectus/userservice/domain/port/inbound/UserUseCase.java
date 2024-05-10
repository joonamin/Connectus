package social.connectus.userservice.domain.port.inbound;

import social.connectus.userservice.common.exception.FailedToLoginException;
import social.connectus.userservice.common.exception.FailedToLogoutException;
import social.connectus.userservice.common.exception.FailedToRegisterUserException;
import social.connectus.userservice.domain.application.response.LoginUserResponse;
import social.connectus.userservice.domain.application.response.LogoutUserResponse;
import social.connectus.userservice.domain.application.response.OpenedPostResponse;
import social.connectus.userservice.domain.port.inbound.command.UserLoginCommand;
import social.connectus.userservice.domain.port.inbound.command.UserLogoutCommand;
import social.connectus.userservice.domain.port.inbound.command.UserRegisterCommand;

public interface UserUseCase {

	// 1. 회원가입
	void register(UserRegisterCommand command) throws FailedToRegisterUserException;

	// 2. 여러개의 서비스에서 호출해서 응답을 조합해서 하나의 usecase 구성하는게 조금 어렵거든요?
	// 특히나 분산 트랜잭션,,, 롤백처리 골치아프거든요

	// 2. 로그인
	LoginUserResponse login(UserLoginCommand command) throws FailedToLoginException;

	void updateOpenedPosts(Long userId, Long postId);
	OpenedPostResponse getOpenedPost(Long userId);
	LogoutUserResponse logout(UserLogoutCommand command) throws FailedToLogoutException;
}
