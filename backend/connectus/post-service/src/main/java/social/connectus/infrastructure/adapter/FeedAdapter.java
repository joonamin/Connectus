package social.connectus.infrastructure.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.response.FeedResponse;
import social.connectus.domain.model.RDBMS.Post;
import social.connectus.domain.ports.outbound.FeedPort;
import social.connectus.infrastructure.databases.mariadb.repository.PostRepository;

@Component
@RequiredArgsConstructor
public class FeedAdapter implements FeedPort {
	private final PostRepository postRepository;

	@Override
	public List<FeedResponse> feedMain(List<Long> walkIdList) {
		List<FeedResponse> feedResponseList = new ArrayList<>();
		for(Long walkId : walkIdList) {
			List<Post> postList = postRepository.findByWalkId(walkId);
			feedResponseList.add(FeedResponse.from(postList));
		}
		return feedResponseList;
	}

	@Override
	public FeedResponse feedDetail(Long walkId) {
		List<Post> postList = postRepository.findByWalkId(walkId);
		return FeedResponse.from(postList);
	}
}
