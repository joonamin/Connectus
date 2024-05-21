package social.connectus.infrastructure.databases.mariadb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import social.connectus.domain.model.RDBMS.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>, CommentRepositoryCustom {
}
