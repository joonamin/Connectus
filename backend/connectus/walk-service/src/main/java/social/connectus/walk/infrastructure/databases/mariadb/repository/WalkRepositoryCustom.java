package social.connectus.walk.infrastructure.databases.mariadb.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import social.connectus.walk.domain.model.VO.Position;
import social.connectus.walk.domain.model.entity.Walk;

import java.util.List;

public interface WalkRepositoryCustom {
    List<Walk> findByUser(long userId);
    Slice<Long> findSliceByPosition(Position position, double distance, long userId, Pageable pageable);
}
