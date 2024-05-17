package social.connectus.infrastructure.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.request.CreateFeedRequestDto;
import social.connectus.application.rest.request.PostRequestDto;
import social.connectus.domain.model.RDBMS.Post;
import social.connectus.domain.ports.outbound.CreatePostPort;
import social.connectus.domain.service.command.PostSpotCommand;
import social.connectus.infrastructure.databases.mariadb.repository.FeedRepository;
import social.connectus.infrastructure.databases.mariadb.repository.PostRepository;
import social.connectus.infrastructure.feignClient.SpotServiceClient;
import social.connectus.infrastructure.feignClient.UserServiceClient;

@Component
@RequiredArgsConstructor
public class CreatePostAdapter implements CreatePostPort {
	private final PostRepository postRepository;
	private final FeedRepository feedRepository;
	private final SpotServiceClient positionServiceClient;
	private final UserServiceClient userServiceClient;
	@Override
	public List<Long> createPost(CreateFeedRequestDto createFeedRequestDto) {
		List<PostSpotCommand> postPositionList = new ArrayList<>();
		List<Long> postIdList = new ArrayList<>();
		List<Post> postList = new ArrayList<>();		// spot update 용 postList
		for(PostRequestDto requestDto : createFeedRequestDto.getPostList()) {
			Post post = Post.from(requestDto);
			postRepository.save(post);
			postIdList.add(post.getId());
			postPositionList.add(PostSpotCommand.from(post,requestDto));
			postList.add(post);
		}
		userServiceClient.updateOpenedPosts(createFeedRequestDto.getPostList().get(0).getAuthorId(),postIdList);
		List<Long> spotIdList = positionServiceClient.insertPostSpot(postPositionList);
		for(int i = 0; i < postList.size(); i++) {
			postList.get(i).setSpotId(spotIdList.get(i));
		}
		postRepository.saveAll(postList);
		return postIdList;
	}
}
