package social.connectus.application.rest.controller;

import java.util.List;

import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.request.CreateCommentRequestDto;
import social.connectus.application.rest.request.CreateFeedRequestDto;
import social.connectus.application.rest.response.DetailPostResponse;
import social.connectus.application.rest.response.FeedResponse;
import social.connectus.application.rest.response.MainPostResponse;
import social.connectus.common.exception.BusinessException;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.exception.NotFoundException;
import social.connectus.common.utils.SliceResponse;
import social.connectus.domain.ports.inbound.CreateCommentUseCase;
import social.connectus.domain.ports.inbound.CreatePostUseCase;
import social.connectus.domain.ports.inbound.DetailPostUseCase;
import social.connectus.domain.ports.inbound.FeedUseCase;
import social.connectus.domain.ports.inbound.MainPostUseCase;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
	private final CreatePostUseCase createPostUseCase;
	private final DetailPostUseCase detailPostUseCase;
	private final CreateCommentUseCase createCommentUseCase;
	private final MainPostUseCase mainPostUseCase;
	private final FeedUseCase feedUseCase;

	// insert의 경우, endWalk에서 이뤄지는 한 walk에 대한 postList를 받아옮
	@PostMapping("/insert")
	public ResponseEntity<String> createPost(@RequestBody CreateFeedRequestDto requestFeed) throws
		GlobalException {
		String result = createPostUseCase.createPost(requestFeed);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Long postId) {
		// deletePostUseCase.deletePost(postId);
		return ResponseEntity.ok().body("delete complete");
	}

	@GetMapping("/mainPostList")
	public ResponseEntity<List<MainPostResponse>> mainPostList(@RequestParam List<Long> id) throws
		BusinessException,
		GlobalException {
		return ResponseEntity.ok().body(mainPostUseCase.mainPost(id));
	}

	@GetMapping("/user-experience/{postId}")
	public ResponseEntity<DetailPostResponse> detailPostByUserExperience(@PathVariable Long postId, Long userId ) throws
		BusinessException,
		GlobalException {
		DetailPostResponse response = detailPostUseCase.detailByUserExperience(postId,userId);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/around/{postId}")
	public ResponseEntity<DetailPostResponse> detailPostByLocation(@PathVariable Long postId, CoordinateRequestDto coordinate) throws
		BusinessException,
		GlobalException {
		DetailPostResponse response = detailPostUseCase.detailByLocation(postId,coordinate);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<DetailPostResponse> detailPostById(@PathVariable Long postId, Long userId, Double distance) throws
		BusinessException,
		GlobalException {
		return ResponseEntity.ok().body(detailPostUseCase.detailByPostId(postId, userId, distance));
	}

	@PostMapping("/{postId}/comment")
	public ResponseEntity<String> createComment(@PathVariable Long postId,@RequestBody CreateCommentRequestDto dto) throws
		GlobalException, NotFoundException {
		String result = createCommentUseCase.createComment(postId, dto);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping("/feed/main")
	public ResponseEntity<SliceResponse<FeedResponse>> feedMain(
		@ModelAttribute CoordinateRequestDto userPosition,
		@RequestParam int pageNum,
		Long userId) throws GlobalException {
		return ResponseEntity.ok().body(feedUseCase.feedMain(userPosition,pageNum,userId));
	}

	@GetMapping("/feed/{walkId}")
	public ResponseEntity<FeedResponse> feedDetail(@PathVariable Long walkId) throws GlobalException {
		return ResponseEntity.ok().body(feedUseCase.feedDetail(walkId));
	}
}
