package social.connectus.infrastructure.adapter;

import org.springframework.stereotype.Component;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.response.DetailPostResponse;
import social.connectus.application.rest.response.OpenedPostResponse;
import social.connectus.common.exception.BusinessException;
import social.connectus.domain.model.RDBMS.Post;
import social.connectus.domain.ports.outbound.DetailPostPort;
import social.connectus.infrastructure.databases.mariadb.repository.PostRepository;
import social.connectus.infrastructure.feignClient.LikesServiceClient;
import social.connectus.infrastructure.feignClient.PositionServiceClient;
import social.connectus.infrastructure.feignClient.UserServiceClient;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DetailPostAdapter implements DetailPostPort {
	private final PostRepository postRepository;
	private final UserServiceClient userServiceClient;
	private final PositionServiceClient positionServiceClient;
	private final LikesServiceClient likesServiceClient;
	@Override
	public DetailPostResponse detailPost(Long postId) throws BusinessException {
		Post post = postRepository.findById(postId).orElseThrow(()->new BusinessException("Post doesn't exist"));
		int likeCount = likesServiceClient.getLikeCount(postId);
		DetailPostResponse response = DetailPostResponse.from(post);
		response.setLikeCount(likeCount);
		return response;
	}

	@Override
	public OpenedPostResponse openedPostByUserId(Long userId) {
		return userServiceClient.getOpenedPost(userId);
	}

	@Override
	public CoordinateRequestDto postPositionByPostId(Long postId) {
		return positionServiceClient.getPostPosition(postId);
	}
}
