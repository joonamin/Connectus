package infrastructure.databases.mariadb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.model.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
