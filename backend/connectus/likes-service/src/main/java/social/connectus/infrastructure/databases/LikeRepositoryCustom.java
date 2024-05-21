package social.connectus.infrastructure.databases;

import java.util.List;

import social.connectus.common.type.Type;

public interface LikeRepositoryCustom {
	Long countByDomainIdAndType(Long domainId, Type type);
	boolean existsByDomainId(Long postId, Long userId, Type type);
	List<Long> findAllByUserId(Long userId);
}
