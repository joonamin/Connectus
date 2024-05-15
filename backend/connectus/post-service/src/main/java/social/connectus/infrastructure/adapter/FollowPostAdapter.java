package social.connectus.infrastructure.adapter;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.response.FollowPostResponse;
import social.connectus.domain.ports.outbound.FollowPostPort;
import social.connectus.infrastructure.feignClient.LocationServiceClient;

@Component
@RequiredArgsConstructor
public class FollowPostAdapter implements FollowPostPort {
	private final LocationServiceClient locationServiceClient;
	@Override
	public FollowPostResponse followPost(Long postId) {
		return locationServiceClient.getPostLocation(postId,"POST");
	}
}
