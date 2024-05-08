package social.connectus.userservice.domain.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import social.connectus.userservice.domain.application.request.RefreshAchievementRequest;
import social.connectus.userservice.domain.application.request.UserLoginRequest;
import social.connectus.userservice.domain.application.request.UserRegisterRequest;
import social.connectus.userservice.domain.application.response.CompletedAchievementListResponse;
import social.connectus.userservice.domain.application.response.LoginUserResponse;
import social.connectus.userservice.domain.application.response.RefreshAchievementResponse;
import social.connectus.userservice.domain.port.inbound.AchievementUseCase;
import social.connectus.userservice.domain.port.inbound.command.UserLoginCommand;
import social.connectus.userservice.domain.port.inbound.command.UserRegisterCommand;
import social.connectus.userservice.domain.port.inbound.UserUseCase;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserUseCase userUseCase;
	private final AchievementUseCase achievementUseCase;

	@PostMapping("/register")
	public ResponseEntity<Void> registerUser(UserRegisterRequest userRegisterRequest) {
		// service 단에서 처리하는 Data Unit의 단위 --> command
		// ModelMapper --> command 객체 만들기위한 메타데이터
		// request -> command
		// request 에서 받은 데이터를 가공하여 command로 만든다.
		userUseCase.register(UserRegisterCommand.from(userRegisterRequest));
		return ResponseEntity.ok().build();
	}

	@PostMapping("/login")
	public ResponseEntity<LoginUserResponse> loginUser(UserLoginRequest userLoginRequest) {
		// request -> command
		LoginUserResponse body = userUseCase.login(UserLoginCommand.from(userLoginRequest));
		return ResponseEntity.ok(body);
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
}
