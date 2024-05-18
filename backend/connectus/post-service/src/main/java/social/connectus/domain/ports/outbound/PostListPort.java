package social.connectus.domain.ports.outbound;

import java.util.List;

public interface PostListPort {
	List<Long> getPostIdList(Long userId);
}
