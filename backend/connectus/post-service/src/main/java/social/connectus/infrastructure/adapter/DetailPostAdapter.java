package social.connectus.infrastructure.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.request.GetPostSpotRequest;
import social.connectus.application.rest.request.SpotDto;
import social.connectus.application.rest.response.CommentResponse;
import social.connectus.application.rest.response.DetailPostResponse;
import social.connectus.application.rest.response.OpenedPostResponse;
import social.connectus.application.rest.response.UserInfoResponse;
import social.connectus.common.exception.BusinessException;
import social.connectus.common.exception.NotFoundException;
import social.connectus.domain.model.RDBMS.Comment;
import social.connectus.domain.model.RDBMS.Post;
import social.connectus.domain.ports.outbound.DetailPostPort;
import social.connectus.infrastructure.databases.mariadb.repository.PostRepository;
import social.connectus.infrastructure.feignClient.LikesServiceClient;
import social.connectus.infrastructure.feignClient.SpotServiceClient;
import social.connectus.infrastructure.feignClient.UserServiceClient;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DetailPostAdapter implements DetailPostPort {
	private final PostRepository postRepository;
	private final UserServiceClient userServiceClient;
	private final SpotServiceClient spotServiceClient;
	private final LikesServiceClient likesServiceClient;

	@Override
	public DetailPostResponse samplePost(Long postId) throws BusinessException, NotFoundException {
		Post post = postRepository.findById(postId).orElseThrow(()->new NotFoundException("Post doesn't exists"));
		int likeCount = likesServiceClient.getLikeCount(postId,"POST");
		boolean isLike = likesServiceClient.isLike(postId,"POST");
		UserInfoResponse authorInfo = userServiceClient.getUserInfo(post.getAuthorId());
		DetailPostResponse response = DetailPostResponse.samplePostFrom(post,authorInfo);
		response.setLikeCount(likeCount);
		response.setLike(isLike);
		return response;
	}

	@Override
	public DetailPostResponse detailPost(Long postId) throws NotFoundException {
		Post post = postRepository.findById(postId).orElseThrow(()-> new NotFoundException("Post doesn't exists"));
		int likeCount = likesServiceClient.getLikeCount(postId,"POST");
		boolean isLike = likesServiceClient.isLike(postId,"POST");
		UserInfoResponse userInfoResponse = userServiceClient.getUserInfo(post.getAuthorId());
		List<CommentResponse> commentResponseList = new ArrayList<>();
		for(Comment comment : post.getCommentList()) {
			UserInfoResponse commentAuthorInfo = userServiceClient.getUserInfo(comment.getAuthorId());
			CommentResponse response = CommentResponse.from(comment,commentAuthorInfo);
			commentResponseList.add(response);
		}

		SpotDto postSpot = postSpotBySpotId(post.getSpotId());
		DetailPostResponse response = DetailPostResponse.detailPostFrom(post, commentResponseList,userInfoResponse, postSpot);
		response.setLike(isLike);
		response.setLikeCount(likeCount);
		return response;
	}

	@Override
	public void updateOpenedPost(Long userId, Long postId) {
		userServiceClient.updateOpenedPost(userId, postId);
	}

	@Override
	public OpenedPostResponse openedPostByUserId(Long userId) {
		return userServiceClient.getOpenedPost(userId);
	}

	@Override
	public SpotDto postSpotBySpotId(Long spotId) {
		List<Long> idList = Arrays.asList(spotId);
		CoordinateRequestDto dto = spotServiceClient.getPostSpot(GetPostSpotRequest.builder().spotIdList(idList).build());
		SpotDto spotDto = dto.getSpotList().get(0);
		return spotDto;
	}

	@Override
	public String healthCheck() {
		return userServiceClient.healthCheck();
	}
}
