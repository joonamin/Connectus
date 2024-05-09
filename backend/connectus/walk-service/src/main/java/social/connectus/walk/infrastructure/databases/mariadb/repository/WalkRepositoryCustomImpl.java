package social.connectus.walk.infrastructure.databases.mariadb.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import social.connectus.walk.domain.model.VO.Position;
import social.connectus.walk.domain.model.entity.QWalk;
import social.connectus.walk.domain.model.entity.Walk;
import java.util.List;

import static social.connectus.walk.domain.model.entity.QWalk.walk;

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

    @Override
    public Slice<Long> findSliceByPosition(Position position, double distance, long userId, Pageable pageable) {

        // TODO: getWalksByPosition
        int pageSize = pageable.getPageSize();
        BooleanBuilder condition = new BooleanBuilder();

        condition.and(walk.route.get(0).latitude.subtract(position.getLatitude()).abs().loe(distance))
                .and(walk.route.get(0).longitude.subtract(position.getLongitude()).abs().loe(distance));

        List<Long> walkIdList = jpaQueryFactory
                .select(walk.walkId)
                .from(walk)
                .fetch();

        boolean hasNext = false;
        if(walkIdList.size() > pageSize){
            walkIdList.remove(pageSize);
            hasNext = true;
        }

        return new SliceImpl<Long>(walkIdList, pageable, hasNext);
    }
}
