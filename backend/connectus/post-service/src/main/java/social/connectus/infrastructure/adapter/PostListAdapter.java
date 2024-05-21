package social.connectus.infrastructure.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.response.MyPagePostResponse;
import social.connectus.domain.model.RDBMS.Post;
import social.connectus.domain.ports.outbound.PostListPort;
import social.connectus.infrastructure.databases.mariadb.repository.PostRepository;
import social.connectus.infrastructure.feignClient.LikesServiceClient;

@Component
@RequiredArgsConstructor
public class PostListAdapter implements PostListPort {
	private final PostRepository postRepository;
	private final LikesServiceClient likesServiceClient;
	@Override
	public List<MyPagePostResponse> getMyPagePostList(Long userId) {
		List<Post> postList = postRepository.findByAuthorId(userId);
		List<MyPagePostResponse> response = new ArrayList<>();
		for(Post post : postList) {
			int likeCount = likesServiceClient.getLikeCount(post.getId(),"POST");
			response.add(MyPagePostResponse.from(post,likeCount));
		}
		return response;
	}

	@Override
	public List<MyPagePostResponse> getMyLikePostList(List<Long> postIdList) {
		List<Post> postList = postRepository.findAllById(postIdList);
		List<MyPagePostResponse> response = new ArrayList<>();
		for(Post post : postList) {
			int likeCount = likesServiceClient.getLikeCount(post.getId(),"POST");
			response.add(MyPagePostResponse.from(post,likeCount));
		}
		return response;
	}
}
