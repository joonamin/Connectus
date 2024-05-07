package social.connectus.infrastructure.databases.mariadb.repository;

import java.util.List;

import social.connectus.domain.model.RDBMS.Post;

public interface PostRepositoryCustom {
	List<Post> findByIdList(List<Long> postIdList);
	List<Post> findByWalkId(Long walkId);
	List<Post> findByWalkIdList(List<Long> walkId);
}
