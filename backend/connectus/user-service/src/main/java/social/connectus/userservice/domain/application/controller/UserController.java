package social.connectus.userservice.domain.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import social.connectus.userservice.domain.application.request.UserAchievementsIndexRequest;
import social.connectus.userservice.domain.application.request.UserAchievmentsIndexRequest;
import social.connectus.userservice.domain.application.request.UserLoginRequest;
import social.connectus.userservice.domain.application.request.UserLogoutRequest;
import social.connectus.userservice.domain.application.request.UserRegisterRequest;
import social.connectus.userservice.domain.application.response.LoginUserResponse;
import social.connectus.userservice.domain.application.response.LogoutUserResponse;
import social.connectus.userservice.domain.application.response.UserAchievmentsIndexResponse;
import social.connectus.userservice.domain.port.inbound.command.UserLoginCommand;
import social.connectus.userservice.domain.port.inbound.command.UserLogoutCommand;
import social.connectus.userservice.domain.port.inbound.command.UserRegisterCommand;
import social.connectus.userservice.domain.port.inbound.UserUseCase;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserUseCase userUseCase;

	@PostMapping("/register")
	public ResponseEntity<Void> registerUser(UserRegisterRequest userRegisterRequest) {
		userUseCase.register(UserRegisterCommand.from(userRegisterRequest));
		return ResponseEntity.ok().build();
	}

	@PostMapping("/login")
	public ResponseEntity<LoginUserResponse> loginUser(UserLoginRequest userLoginRequest) {
		LoginUserResponse body = userUseCase.login(UserLoginCommand.from(userLoginRequest));
		return ResponseEntity.ok(body);
	}

	@PostMapping("/logout")
	public ResponseEntity<LogoutUserResponse> logoutUser(UserLogoutRequest userLogoutRequest) {
		LogoutUserResponse response = userUseCase.logout(UserLogoutCommand.from(userLogoutRequest));
		return ResponseEntity.ok(response);
	}

	// get 업적 관련 지표
	@GetMapping("/achievements/index")
	public ResponseEntity<UserAchievmentsIndexResponse> getUserAchievementsIndex(UserAchievementsIndexRequest request) {
		return null;
	}

}
