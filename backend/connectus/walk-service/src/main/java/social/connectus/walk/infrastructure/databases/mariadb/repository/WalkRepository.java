package social.connectus.walk.infrastructure.databases.mariadb.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import social.connectus.walk.domain.model.VO.Position;
import social.connectus.walk.domain.model.entity.Walk;

@Repository
public interface WalkRepository extends JpaRepository<Walk, Long>, WalkRepositoryCustom {
}
