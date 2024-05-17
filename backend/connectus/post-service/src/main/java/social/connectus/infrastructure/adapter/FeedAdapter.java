package social.connectus.infrastructure.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.response.FeedResponse;
import social.connectus.application.rest.response.UserInfoResponse;
import social.connectus.domain.model.RDBMS.Post;
import social.connectus.domain.ports.outbound.FeedPort;
import social.connectus.infrastructure.databases.mariadb.repository.PostRepository;
import social.connectus.infrastructure.feignClient.UserServiceClient;

@Component
@RequiredArgsConstructor
public class FeedAdapter implements FeedPort {
	private final PostRepository postRepository;
	private final UserServiceClient userServiceClient;

	@Override
	public List<FeedResponse> feedMain(List<Long> walkIdList) {
		List<FeedResponse> feedResponseList = new ArrayList<>();
		for(Long walkId : walkIdList) {
			List<Post> postList = postRepository.findByWalkId(walkId);
			UserInfoResponse userInfo = userServiceClient.getUserInfo(postList.get(0).getAuthorId());
			feedResponseList.add(FeedResponse.from(postList, userInfo));
		}
		return feedResponseList;
	}

	@Override
	public FeedResponse feedDetail(Long walkId) {
		List<Post> postList = postRepository.findByWalkId(walkId);
		UserInfoResponse userInfo = userServiceClient.getUserInfo(postList.get(0).getAuthorId());
		return FeedResponse.from(postList, userInfo);
	}
}
