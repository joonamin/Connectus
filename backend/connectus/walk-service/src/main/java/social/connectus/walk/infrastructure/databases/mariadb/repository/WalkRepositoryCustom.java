package social.connectus.walk.infrastructure.databases.mariadb.repository;

import social.connectus.walk.domain.model.entity.Walk;

import java.util.List;

public interface WalkRepositoryCustom {
    List<Walk> findByUser(long userId);
}
