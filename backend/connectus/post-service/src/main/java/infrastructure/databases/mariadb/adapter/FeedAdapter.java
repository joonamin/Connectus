package infrastructure.databases.mariadb.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import application.rest.request.CoordinateRequestDto;
import application.rest.response.DetailPostResponse;
import application.rest.response.FeedResponse;
import domain.model.Feed;
import domain.model.Post;
import domain.ports.outbound.FeedPort;
import infrastructure.databases.mariadb.repository.FeedRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FeedAdapter implements FeedPort {
	private final FeedRepository feedRepository;
	@Override
	public FeedResponse feedDetail(Long walkId) {
		Feed feed = feedRepository.findByWalkId(walkId);
		List<DetailPostResponse> responsePostList = new ArrayList<>();
		for(Post post : feed.getPostList()) {
			DetailPostResponse detailPostResponse = DetailPostResponse.from(post);
			responsePostList.add(detailPostResponse);
		}
		return FeedResponse.builder()
			.walkId(walkId)
			.postList(responsePostList)
			.build();
	}

}
