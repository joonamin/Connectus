package social.connectus.walk.infrastructure.databases.mariadb.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import social.connectus.walk.domain.model.entity.QWalk;
import social.connectus.walk.domain.model.entity.Walk;

import java.util.List;

@RequiredArgsConstructor
public class WalkRepositoryCustomImpl implements WalkRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Walk> findByUser(long userId) {
        QWalk walk = QWalk.walk;
        return jpaQueryFactory.selectFrom(walk)
                .where(walk.userId.eq(userId))
                .fetch();
    }
}
