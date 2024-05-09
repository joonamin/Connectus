package social.connectus.walk.infrastructure.databases.mariadb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import social.connectus.walk.domain.model.entity.CompletedAchievement;
import social.connectus.walk.domain.model.entity.Route;
import social.connectus.walk.domain.model.entity.Walk;

import java.util.List;
import java.util.Set;

@Repository
public interface WalkRepository extends JpaRepository<Walk, Long>, WalkRepositoryCustom {
}
