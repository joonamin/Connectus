package social.connectus.application.rest.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.ws.rs.Produces;
import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.request.CreateCommentRequestDto;
import social.connectus.application.rest.request.CreateFeedRequestDto;
import social.connectus.application.rest.response.DetailPostResponse;
import social.connectus.application.rest.response.FeedResponse;
import social.connectus.application.rest.response.FollowPostResponse;
import social.connectus.application.rest.response.MainPostResponse;
import social.connectus.common.exception.BusinessException;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.exception.NotFoundException;
import social.connectus.common.utils.SliceResponse;
import social.connectus.domain.ports.inbound.CreateCommentUseCase;
import social.connectus.domain.ports.inbound.CreatePostUseCase;
import social.connectus.domain.ports.inbound.DetailPostUseCase;
import social.connectus.domain.ports.inbound.FeedUseCase;
import social.connectus.domain.ports.inbound.FollowPostUseCase;
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
	private final FollowPostUseCase followPostUseCase;

	// insert의 경우, endWalk에서 이뤄지는 한 walk에 대한 postList를 받아옮
	@PostMapping("/insert")
	@Operation(
		summary = "방명록 생성하기",
		description = "createWalk에서 생성 확정한 postList를 저장합니다."
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "post 저장 성공"
		)
	})
	public ResponseEntity<List<Long>> createPost(
		@RequestBody CreateFeedRequestDto requestFeed
	) throws
		GlobalException, IOException {
		return ResponseEntity.ok().body(createPostUseCase.createPost(requestFeed));
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable("postId") Long postId) {
		// deletePostUseCase.deletePost(postId);
		return ResponseEntity.ok().body("delete complete");
	}

	@GetMapping("/mainPostList")
	@Operation(
		summary = "postList 불러오기",
		description = "content의 일부를 가진 postList를 가져옵니다. (리스트를 부를 때 client에서 detail을 여러번 호출 할 지 리스트를 한번 호출 할 지 몰라서 사용 안 할 수도?)"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "post list 불러오기 성공"
		)
	})
	public ResponseEntity<List<MainPostResponse>> mainPostList(@RequestParam("id") List<Long> id) throws
		BusinessException,
		GlobalException {
		return ResponseEntity.ok().body(mainPostUseCase.mainPost(id));
	}

	@GetMapping("/user-experience/{postId}")
	public ResponseEntity<DetailPostResponse> detailPostByUserExperience(@PathVariable("postId") Long postId, Long userId ) throws
		BusinessException,
		GlobalException {
		DetailPostResponse response = detailPostUseCase.detailByUserExperience(postId,userId);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/around/{postId}")
	public ResponseEntity<DetailPostResponse> detailPostByLocation(@PathVariable("postId") Long postId, CoordinateRequestDto coordinate) throws
		BusinessException,
		GlobalException {
		DetailPostResponse response = detailPostUseCase.detailByLocation(postId,coordinate);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/{postId}")
	@Operation(
		summary = "post 불러오기",
		description = "request로 받은 id에 해당하는 post detail 리턴 (postList를 띄울 때 여러번 호출)"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "post 불러오기 성공"
		)
	})
	public ResponseEntity<DetailPostResponse> detailPostById(@PathVariable("postId") Long postId, @RequestParam("userId") Long userId, @RequestParam("distance") Double distance) throws
		BusinessException,
		GlobalException {
		return ResponseEntity.ok().body(detailPostUseCase.detailByPostId(postId, userId, distance));
	}

	@PostMapping("/{postId}/comment")
	@Operation(
		summary = "댓글 생성",
		description = "댓글을 저장합니다."
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "comment 저장 성공"
		)
	})
	public ResponseEntity<String> createComment(@PathVariable("postId") Long postId,@RequestBody CreateCommentRequestDto dto) throws
		GlobalException, NotFoundException {
		String result = createCommentUseCase.createComment(postId, dto);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping("/feed/main")
	@Operation(
		summary = "피드 메인 불러오기",
		description = "유저 위치에서 가까운 feed List를 불러옴."
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "feed list 불러오기 성공"
		)
	})
	public ResponseEntity<SliceResponse<FeedResponse>> feedMain(
		@ModelAttribute CoordinateRequestDto userPosition,
		@RequestParam("pageNum") int pageNum,
		Long userId) throws GlobalException {
		return ResponseEntity.ok().body(feedUseCase.feedMain(userPosition,pageNum,userId));
	}

	@Operation(
		summary = "피드 상세 정보",
		description = "피드의 상세 정보를 불러옴"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "feed detail 불러오기 성공"
		)
	})
	@GetMapping("/feed/{walkId}")
	public ResponseEntity<FeedResponse> feedDetail(@PathVariable("walkId") Long walkId) throws GlobalException {
		return ResponseEntity.ok().body(feedUseCase.feedDetail(walkId));
	}

	@GetMapping("/post-to-user")
	public ResponseEntity<String> postToUser() {
		return ResponseEntity.ok(detailPostUseCase.healthCheck());
	}

	@Operation(
		summary = "방명록 따라가기",
		description = "post 위치 리턴"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "post location 불러오기 성공"
		)
	})
	@GetMapping("/follow/{postId}")
	public ResponseEntity<FollowPostResponse> followPost(@PathVariable("postId") Long postId) throws GlobalException {
		return ResponseEntity.ok(followPostUseCase.followPost(postId));
	}

	@GetMapping("/health-check")
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("Check Good!");
	}
}
