package social.connectus.walk.infrastructure.databases.mariadb.repository;

import java.util.List;

import social.connectus.walk.domain.model.entity.Walk;

public interface LikeUserRepositoryCustom {
	List<Walk> findAllByUserId(Long userId);
	Long countByWalkId(Long walkId);
}
