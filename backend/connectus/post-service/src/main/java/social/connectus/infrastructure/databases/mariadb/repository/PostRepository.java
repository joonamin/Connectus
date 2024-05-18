package social.connectus.infrastructure.databases.mariadb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import social.connectus.domain.model.RDBMS.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
	List<Long> findAllByUserId(Long userId);
}
