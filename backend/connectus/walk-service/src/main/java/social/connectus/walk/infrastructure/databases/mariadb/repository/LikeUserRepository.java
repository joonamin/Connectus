package social.connectus.walk.infrastructure.databases.mariadb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import social.connectus.walk.domain.model.entity.LikeUser;

import java.util.List;

public interface LikeUserRepository extends JpaRepository<LikeUser, Long> {
    List<LikeUser> findByUserIdAndWalkId(long userId, long walkId);
}
