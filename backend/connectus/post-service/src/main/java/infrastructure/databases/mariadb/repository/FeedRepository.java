package infrastructure.databases.mariadb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.model.Feed;

public interface FeedRepository extends JpaRepository<Feed,Long> {
	Feed findByWalkId(Long walkId);
}
