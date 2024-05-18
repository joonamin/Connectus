package social.connectus.infrastructure.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import social.connectus.domain.ports.outbound.PostListPort;
import social.connectus.infrastructure.databases.mariadb.repository.PostRepository;

@Component
@RequiredArgsConstructor
public class PostListAdapter implements PostListPort {
	private final PostRepository postRepository;
	@Override
	public List<Long> getPostIdList(Long userId) {
		return postRepository.findAllByUserId(userId);
	}
}
