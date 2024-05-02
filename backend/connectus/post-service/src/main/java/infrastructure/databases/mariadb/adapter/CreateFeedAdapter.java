package infrastructure.databases.mariadb.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.querydsl.jpa.impl.JPAQueryFactory;

import application.rest.request.CoordinateRequestDto;
import application.rest.request.CreateFeedRequestDto;
import application.rest.request.PostRequestDto;
import domain.model.Feed;
import domain.model.Post;
import domain.ports.outbound.CreateFeedPort;
import infrastructure.databases.mariadb.repository.FeedRepository;
import infrastructure.databases.mariadb.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateFeedAdapter implements CreateFeedPort {
	private final PostRepository postRepository;
	private final FeedRepository feedRepository;
	private final JPAQueryFactory jpaQueryFactory;
	@Override
	public String createFeed(CreateFeedRequestDto requestFeed) {
		List<Post> postList = new ArrayList<>();
		for(PostRequestDto dto : requestFeed.getPostList()) {
			Post post = Post.from(dto);
			post = postRepository.save(post);
			postList.add(post);

			CoordinateRequestDto coordinateRequestDto = dto.getCoordinateRequestDto();
			// {type : post, latitude : coordinate.getLatitude(), longitude : coordinate.getLongitude, domainId : post.getId}
			// locationRepository.save(위의 json) << kafka에 올리나?

		}
		Feed feed = Feed.builder()
			.walkId(requestFeed.getWalkId())
			.postList(postList)
			.build();

		feedRepository.save(feed);
		return "feed save";
	}
}
