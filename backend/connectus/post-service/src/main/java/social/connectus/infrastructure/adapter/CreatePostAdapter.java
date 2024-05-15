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
import social.connectus.domain.service.command.PostPositionCommand;
import social.connectus.infrastructure.databases.mariadb.repository.FeedRepository;
import social.connectus.infrastructure.databases.mariadb.repository.PostRepository;
import social.connectus.infrastructure.feignClient.PositionServiceClient;

@Component
@RequiredArgsConstructor
public class CreatePostAdapter implements CreatePostPort {
	private final PostRepository postRepository;
	private final FeedRepository feedRepository;
	private final PositionServiceClient positionServiceClient;
	@Override
	public List<Long> createPost(CreateFeedRequestDto createFeedRequestDto) {
		List<PostPositionCommand> postPositionList = new ArrayList<>();
		List<Long> postIdList = new ArrayList<>();
		for(PostRequestDto requestDto : createFeedRequestDto.getPostList()) {
			Post post = Post.from(requestDto);
			postRepository.save(post);
			postIdList.add(post.getId());
			postPositionList.add(PostPositionCommand.from(post,requestDto));
		}
		positionServiceClient.insertPostPosition(postPositionList);

		return postIdList;
	}
}
