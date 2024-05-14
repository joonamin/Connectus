package social.connectus.infrastructure.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.request.CreateFeedRequestDto;
import social.connectus.application.rest.request.PostRequestDto;
import social.connectus.domain.model.RDBMS.Feed;
import social.connectus.domain.model.RDBMS.Post;
import social.connectus.domain.ports.outbound.CreatePostPort;
import social.connectus.domain.service.command.InsertPostCommand;
import social.connectus.infrastructure.databases.mariadb.repository.FeedRepository;
import social.connectus.infrastructure.databases.mariadb.repository.PostRepository;

@Component
@RequiredArgsConstructor
public class CreatePostAdapter implements CreatePostPort {
	private final PostRepository postRepository;
	private final FeedRepository feedRepository;
	@Override
	public List<Long> createPost(Long walkId, List<InsertPostCommand> postCommandList) {
		List<Post> postList = new ArrayList<>();
		for(InsertPostCommand request : postCommandList) {
			Post post = Post.from(request);
			postList.add(post);
		}
		List<Long> postIdList = postRepository.saveAll(postList).stream().map(Post::getId).toList();
		feedRepository.save(Feed.from(walkId,postIdList));

		return postIdList;
	}
}
