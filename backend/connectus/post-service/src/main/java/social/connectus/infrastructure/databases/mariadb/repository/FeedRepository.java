package social.connectus.infrastructure.databases.mariadb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import social.connectus.domain.model.RDBMS.Feed;

public interface FeedRepository extends JpaRepository<Feed,Long>, FeedRepositoryCustom {
}
