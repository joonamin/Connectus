package social.connectus.domain.ports.outbound;

import social.connectus.common.type.Type;

public interface LikePort {
	String insertLike(Long domainId, Long userId, Type type);

	int getLikeCount(Long domainId, Type type);

	boolean isLike(Long domainId);
}
