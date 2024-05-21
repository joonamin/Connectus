package social.connectus.walk.infrastructure.databases.mariadb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import social.connectus.walk.domain.model.entity.LikeUser;

import java.util.List;

@Repository
public interface LikeUserRepository extends JpaRepository<LikeUser, Long>,LikeUserRepositoryCustom {
    List<LikeUser> findByUserIdAndWalkId(long userId, long walkId);
}
