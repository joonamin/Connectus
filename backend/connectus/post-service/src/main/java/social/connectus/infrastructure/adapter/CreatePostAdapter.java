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
import social.connectus.infrastructure.databases.mariadb.repository.PostRepository;

@Component
@RequiredArgsConstructor
public class CreatePostAdapter implements CreatePostPort {
	private final PostRepository postRepository;
	@Override
	public String createPost(CreateFeedRequestDto requestDto) {
		List<Post> postList = new ArrayList<>();
		for(PostRequestDto request : requestDto.getPostList()) {
			Post post = Post.from(request);
			postList.add(post);
		}
		postRepository.saveAll(postList);
		List<Long> postIdList = postList.stream().map(Post::getId).toList();
		Feed.from(requestDto,postIdList);

		return "Complete save Feed";
	}
}
