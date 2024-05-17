package social.connectus.infrastructure.adapter;

import org.springframework.stereotype.Component;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.request.GetPostSpotRequest;
import social.connectus.application.rest.response.FollowPostResponse;
import social.connectus.domain.model.RDBMS.Post;
import social.connectus.domain.ports.outbound.FollowPostPort;
import social.connectus.infrastructure.databases.mariadb.repository.PostRepository;
import social.connectus.infrastructure.feignClient.LocationServiceClient;
import social.connectus.infrastructure.feignClient.SpotServiceClient;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class FollowPostAdapter implements FollowPostPort {
	private final SpotServiceClient spotServiceClient;
	private final PostRepository postRepository;
	@Override
	public FollowPostResponse followPost(Long postId) {
		Post post = postRepository.findById(postId).orElseThrow(()->new NotFoundException("user not exists"));
		return FollowPostResponse
				.from(spotServiceClient
						.getPostSpot(GetPostSpotRequest.builder()
								.spotIdList(Arrays.asList(post.getSpotId()))
								.build()).getSpotList().get(0)
						,postId);
	}
}
