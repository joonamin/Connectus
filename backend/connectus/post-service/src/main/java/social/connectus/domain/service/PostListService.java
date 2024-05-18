package social.connectus.domain.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import social.connectus.common.annotation.UseCase;
import social.connectus.domain.ports.inbound.PostListUseCase;
import social.connectus.domain.ports.outbound.PostListPort;

@UseCase
@RequiredArgsConstructor
public class PostListService implements PostListUseCase {
	private final PostListPort postListPort;
	@Override
	public List<Long> getPostIdList(Long userId) {
		return postListPort.getPostIdList(userId);
	}
}
