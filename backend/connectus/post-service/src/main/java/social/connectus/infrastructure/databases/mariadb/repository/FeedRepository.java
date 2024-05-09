package social.connectus.infrastructure.databases.mariadb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import social.connectus.domain.model.RDBMS.Feed;

@Repository
public interface FeedRepository extends JpaRepository<Feed,Long>, FeedRepositoryCustom {
}
