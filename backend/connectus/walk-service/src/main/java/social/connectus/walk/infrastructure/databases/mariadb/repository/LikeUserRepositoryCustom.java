package social.connectus.walk.infrastructure.databases.mariadb.repository;

import java.util.List;

public interface LikeUserRepositoryCustom {
	List<Long> findAllByUserId(Long userId);
}
