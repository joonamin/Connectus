package infrastructure.databases.mariadb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
