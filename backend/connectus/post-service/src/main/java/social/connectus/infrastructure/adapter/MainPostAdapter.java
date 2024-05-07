package social.connectus.infrastructure.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.response.MainPostResponse;
import social.connectus.domain.model.RDBMS.Post;
import social.connectus.domain.ports.outbound.MainPostPort;
import social.connectus.infrastructure.databases.mariadb.repository.CommentRepository;
import social.connectus.infrastructure.databases.mariadb.repository.PostRepository;
import social.connectus.infrastructure.feignClient.LikesServiceClient;

@Component
@RequiredArgsConstructor
public class MainPostAdapter implements MainPostPort {
	private final PostRepository postRepository;
	private final LikesServiceClient likesServiceClient;
	private final CommentRepository commentRepository;
	@Override
	public List<MainPostResponse> getMainPost(List<Long> postId) {
		List<Post> postList = postRepository.findByIdList(postId);
		List<MainPostResponse> response = new ArrayList<>();
		for(Post post : postList) {
			int likeCount = likesServiceClient.getLikeCount(post.getId());
			response.add(MainPostResponse.from(post,likeCount));
		}
		return response;
	}
}
