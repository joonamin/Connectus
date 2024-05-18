package social.connectus.domain.ports.inbound;

import java.util.List;

import social.connectus.common.annotation.UseCase;

public interface PostListUseCase {
	List<Long> getPostIdList(Long userId);
}
