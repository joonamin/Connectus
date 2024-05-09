package social.connectus.userservice.domain.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import social.connectus.userservice.domain.application.request.RefreshAchievementRequest;
import social.connectus.userservice.domain.application.request.UserAchievementsIndexRequest;
import social.connectus.userservice.domain.application.request.UserLoginRequest;
import social.connectus.userservice.domain.application.request.UserLogoutRequest;
import social.connectus.userservice.domain.application.request.UserRegisterRequest;
import social.connectus.userservice.domain.application.response.CompletedAchievementListResponse;
import social.connectus.userservice.domain.application.response.LoginUserResponse;
import social.connectus.userservice.domain.application.response.LogoutUserResponse;
import social.connectus.userservice.domain.application.response.OpenedPostResponse;
import social.connectus.userservice.domain.application.response.RefreshAchievementResponse;
import social.connectus.userservice.domain.application.response.UserAchievmentsIndexResponse;
import social.connectus.userservice.domain.port.inbound.AchievementUseCase;
import social.connectus.userservice.domain.port.inbound.UserUseCase;
import social.connectus.userservice.domain.port.inbound.command.UserLoginCommand;
import social.connectus.userservice.domain.port.inbound.command.UserLogoutCommand;
import social.connectus.userservice.domain.port.inbound.command.UserRegisterCommand;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserUseCase userUseCase;
	private final AchievementUseCase achievementUseCase;

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

	@GetMapping("/{userId}/openedPosts")
	public ResponseEntity<OpenedPostResponse> getOpenedPost(@PathVariable Long userId) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		log.info(objectMapper.writeValueAsString(userUseCase.getOpenedPost(userId)));
		return ResponseEntity.ok(userUseCase.getOpenedPost(userId));
	}

	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return ResponseEntity.ok("good");
	}
}
