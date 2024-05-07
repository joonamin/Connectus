package social.connectus.application.rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.ws.rs.Path;
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
import social.connectus.domain.ports.inbound.CreateCommentUseCase;
import social.connectus.domain.ports.inbound.CreatePostUseCase;
import social.connectus.domain.ports.inbound.DetailPostUseCase;
import social.connectus.domain.ports.inbound.FeedUseCase;
import social.connectus.domain.ports.inbound.MainPostUseCase;

@RestController
@RequestMapping("/post-service")
@RequiredArgsConstructor
public class PostController {
	private final CreatePostUseCase createPostUseCase;
	private final DetailPostUseCase detailPostUseCase;
	private final CreateCommentUseCase createCommentUseCase;
	private final MainPostUseCase mainPostUseCase;
	private final FeedUseCase feedUseCase;
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
	public ResponseEntity<DetailPostResponse> detailPostById(@PathVariable Long postId, Long userId, CoordinateRequestDto coordinate) throws
		BusinessException,
		GlobalException {
		return ResponseEntity.ok().body(detailPostUseCase.detailByPostId(postId, userId, coordinate));
	}

	@PostMapping("/{postId}/comment")
	public ResponseEntity<String> createComment(@PathVariable Long postId, CreateCommentRequestDto dto) throws
		GlobalException, NotFoundException {
		String result = createCommentUseCase.createComment(postId, dto);
		return ResponseEntity.ok().body(result);
	}

	/* TODO : 생각해보니까 얘는 slice로 리턴해야되는데 좀 더 생각해보고 짭시다
		근데 피드로 슬라이스를 자르려면 feed entity가 따로 필요한데 정신 나가겠네
		슬라이스 처리는 위치 서비스에서 진행하고 post에서는 받은 idlist들을 뿌려주는 역할만?
	 */
	@GetMapping("/feed/main")
	public ResponseEntity<List<FeedResponse>> feedMain(@RequestParam List<Long> walkId) throws GlobalException {
		return ResponseEntity.ok().body(feedUseCase.feedMain(walkId));
	}

	@GetMapping("/feed/{walkId}")
	public ResponseEntity<FeedResponse> feedDetail(@PathVariable Long walkId) throws GlobalException {
		return ResponseEntity.ok().body(feedUseCase.feedDetail(walkId));
	}
}
