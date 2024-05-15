package social.connectus.infrastructure.databases;

import social.connectus.common.type.Type;

public interface LikeRepositoryCustom {
	Long countByDomainIdAndType(Long domainId, Type type);
	boolean existsByDomainId(Long domainId, Type type);
}
