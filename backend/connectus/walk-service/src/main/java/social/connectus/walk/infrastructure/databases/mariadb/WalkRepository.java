package social.connectus.walk.infrastructure.databases.mariadb;

import org.springframework.data.jpa.repository.JpaRepository;
import social.connectus.walk.domain.model.entity.Walk;

public interface WalkRepository extends JpaRepository<Walk, Long> {
}
