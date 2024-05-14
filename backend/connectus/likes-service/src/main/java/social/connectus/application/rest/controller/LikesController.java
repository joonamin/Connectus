package social.connectus.application.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.request.LikeRequest;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.type.Type;
import social.connectus.domain.ports.inbound.LikeUseCase;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {
	private final LikeUseCase likeUseCase;

	@GetMapping("/health_check")
	public String health_check() {
		return "It's working on gather-service!";
	}

	@PostMapping("/insert")
	@Operation(
		summary = "좋아요 등록",
		description = "post 혹은 route를(type을 통해 구별) 좋아요 등록합니다."
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "like 저장 성공"
		)
	})
	public ResponseEntity<String> insertPostLike(@RequestBody LikeRequest likeRequest) throws GlobalException {
		Type type = Type.valueOf(likeRequest.getType());
		return ResponseEntity.ok(
			likeUseCase.insertLike(likeRequest.getDomainId(), likeRequest.getUserId(), type));
	}

	@GetMapping("/{domainId}/like-count")
	@Operation(
		summary = "좋아요 수 리턴",
		description = "domainId와 type에 해당하는 좋아요 개수를 리턴합니다."
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "likeCount 리턴 성공"
		)
	})
	public ResponseEntity<Integer> getLikeCount(@PathVariable Long domainId, @RequestParam String typeString) throws
		GlobalException {
		Type type = Type.valueOf(typeString);
		return ResponseEntity.ok(likeUseCase.getLikeCount(domainId, type));
	}

	@GetMapping("/{domainId}/is-like")
	@Operation(
		summary = "좋아요 조회",
		description = "domainId와 type에 해당하는 게시물에 유저의 좋아요 여부를 리턴합니다."
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "좋아요 여부 리턴"
		)
	})
	public ResponseEntity<Boolean> isLike(@PathVariable Long domainId, @RequestParam String typeString){
		Type type = Type.valueOf(typeString);
		return ResponseEntity.ok(likeUseCase.isLike(domainId,type));
	}
}
