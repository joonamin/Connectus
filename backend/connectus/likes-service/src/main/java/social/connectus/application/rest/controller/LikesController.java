package social.connectus.application.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<String> insertPostLike(@RequestBody LikeRequest likeRequest) throws GlobalException {
		return ResponseEntity.ok(
			likeUseCase.insertLike(likeRequest.getDomainId(), likeRequest.getUserId(), likeRequest.getType()));
	}

	@GetMapping("/{domainId}/like-count")
	public ResponseEntity<Integer> getLikeCount(@PathVariable Long domainId, @RequestParam Type type) throws
		GlobalException {
		return ResponseEntity.ok(likeUseCase.getLikeCount(domainId, type));
	}

	@GetMapping("/{domainId}/is-like")
	public ResponseEntity<Boolean> isLike(@PathVariable Long domainId){
		return ResponseEntity.ok(likeUseCase.isLike(domainId));
	}
}
