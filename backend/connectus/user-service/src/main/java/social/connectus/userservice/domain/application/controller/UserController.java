package social.connectus.userservice.domain.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import social.connectus.userservice.domain.application.request.MyWalkRequest;
import social.connectus.userservice.domain.application.request.RefreshAchievementRequest;
import social.connectus.userservice.domain.application.request.UserAchievementsIndexRequest;
import social.connectus.userservice.domain.application.request.UserLoginRequest;
import social.connectus.userservice.domain.application.request.UserLogoutRequest;
import social.connectus.userservice.domain.application.request.UserRegisterRequest;
import social.connectus.userservice.domain.application.response.CompletedAchievementListResponse;
import social.connectus.userservice.domain.application.response.LoginUserResponse;
import social.connectus.userservice.domain.application.response.LogoutUserResponse;
import social.connectus.userservice.domain.application.response.MyWalkResponse;
import social.connectus.userservice.domain.application.response.RefreshAchievementResponse;
import social.connectus.userservice.domain.application.response.UserAchievmentsIndexResponse;
import social.connectus.userservice.domain.port.inbound.AchievementUseCase;
import social.connectus.userservice.domain.port.inbound.UserUseCase;
import social.connectus.userservice.domain.port.inbound.WalkUseCase;
import social.connectus.userservice.domain.port.inbound.command.UserLoginCommand;
import social.connectus.userservice.domain.port.inbound.command.UserLogoutCommand;
import social.connectus.userservice.domain.port.inbound.command.UserRegisterCommand;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserUseCase userUseCase;
	private final AchievementUseCase achievementUseCase;
	private final WalkUseCase walkUseCase;

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


	@GetMapping("/completed-achievement/{userId}")
	public ResponseEntity<CompletedAchievementListResponse> getUserCompletedAchievement(@PathVariable Long userId) {
		return ResponseEntity.ok().body(achievementUseCase.checkAchievement(userId));
	}

	// endWalk 때 statistics를 갱신하기 위한 controller 이번 refresh를 통해 새로 완료한 업적을 출력
	@PostMapping("/refresh-achievement")
	public ResponseEntity<RefreshAchievementResponse> refreshAchievement(Long userId,
		@RequestBody RefreshAchievementRequest statistics) {
		return ResponseEntity.ok(achievementUseCase.refreshAchievement(userId, statistics));
	}

	// 내 산책 기록
	@GetMapping("/me/walk")
	public ResponseEntity<MyWalkResponse> getMyWalk(MyWalkRequest request) {

	}
}
