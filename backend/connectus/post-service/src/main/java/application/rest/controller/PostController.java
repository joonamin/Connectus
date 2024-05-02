package application.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.rest.request.CoordinateRequestDto;
import application.rest.request.CreateCommentRequestDto;
import application.rest.request.CreateFeedRequestDto;
import application.rest.response.CreateCommentResponse;
import application.rest.response.CreatePostResponse;
import application.rest.response.DetailPostResponse;
import application.rest.response.FeedResponse;
import common.exception.BusinessException;
import common.exception.GlobalException;
import common.exception.ParameterNotFoundException;
import domain.ports.inbound.CreateCommentUseCase;
import domain.ports.inbound.CreateFeedUseCase;
import domain.ports.inbound.DetailPostUseCase;
import domain.ports.inbound.FeedUseCase;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/post")
@RequiredArgsConstructor
public class PostController {
	private final CreateFeedUseCase createFeedUseCase;
	private final DetailPostUseCase detailPostUseCase;
	private final CreateCommentUseCase createCommentUseCase;
	private final FeedUseCase feedUseCase;
	@PostMapping()
	public ResponseEntity<String> createFeed(CreateFeedRequestDto requestFeed) throws
		ParameterNotFoundException,
		GlobalException {
		String result = createFeedUseCase.createFeed(requestFeed);
		return ResponseEntity.ok().body(result);
	}
	@DeleteMapping("/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Long postId) {
		// deletePostUseCase.deletePost(postId);
		return ResponseEntity.ok().body("delete complete");
	}

	@GetMapping("/user-experience/{postId}")
	public ResponseEntity<DetailPostResponse> detailPostByUserExperience(@PathVariable Long postId, Long userId ) throws
		BusinessException,
		GlobalException { // TODO: 예전엔 AuthenticationPrincipal로 받은 userId 어떻게 받을지는 좀 더 고민해봅시다잉!
		DetailPostResponse response = detailPostUseCase.detailByUserExperience(postId,userId);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/around/{postId}")
	public ResponseEntity<DetailPostResponse> detailPostByLocation(@PathVariable Long postId, CoordinateRequestDto coordinate) throws
		BusinessException,
		GlobalException {
		// TODO: 예전엔 AuthenticationPrincipal로 받은 userId 어떻게 받을지는 좀 더 고민해봅시다잉!
		DetailPostResponse response = detailPostUseCase.detailByLocation(postId,coordinate);
		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/{postId}/comment")
	public ResponseEntity<String> createComment(@PathVariable Long postId, CreateCommentRequestDto dto) throws
		GlobalException {
		String result = createCommentUseCase.createComment(postId, dto);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping("/feed/{walkId}")
	public ResponseEntity<FeedResponse> feedDetail(@PathVariable Long walkId) throws GlobalException {
		return ResponseEntity.ok().body(feedUseCase.feedDetail(walkId));
	}
}
