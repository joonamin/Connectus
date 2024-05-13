package social.connectus.walk.infrastructure.databases.mariadb.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.MathExpressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import social.connectus.walk.domain.model.VO.Position;
import social.connectus.walk.domain.model.entity.QWalk;
import social.connectus.walk.domain.model.entity.Route;
import social.connectus.walk.domain.model.entity.Walk;
import social.connectus.walk.domain.ports.inbound.WalkUseCase;

import java.util.Date;
import java.util.List;

import static social.connectus.walk.domain.model.entity.QWalk.walk;
import static social.connectus.walk.domain.model.entity.QRoute.route;

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

//    @Override
//    public Slice<Long> findSliceByPosition(Position position, double kmRadius, long userId, Pageable pageable) {
//        int pageSize = pageable.getPageSize();
//        double userLatitude = position.getLatitude();
//        double userLongitude = position.getLongitude();
//
//        BooleanBuilder builder = new BooleanBuilder();
//
//
//        NumberExpression<Double> test = MathExpressions.power(route.latitude, 2).doubleValue();
//        NumberExpression<Double> test2 = MathExpressions.power(route.longitude,2).doubleValue();
//        NumberExpression<Double> test3 = test.add(test2).doubleValue();
//
////        builder.or(MathExpressions.power(route.longitude, 2).add(route.longitude).loe(40D));
////        builder.or(route.longitude.add(route.longitude).loe(10D));
//        NumberExpression<Double> ne1 = route.latitude.add(1);
//        builder.or(ne1.add(route.longitude).loe(10D));
//
////        List<Route> routeList = jpaQueryFactory
////                .select(route)
////                .from(route)
////                .groupBy(route.walk)
////                .having(isRouteInDistance(userLatitude, userLongitude).loe(kmRadius))
////                .fetch();
//
//        List<Route> routeList = jpaQueryFactory
//                .select(route)
//                .from(route)
//                .groupBy(route.walk)
//                .having(builder)
//                .fetch();
//
//
//        List<Long> walkIdList = routeList.stream()
//                .map(routeItem -> routeItem.getWalk().getId())
//                .toList();
//
//        boolean hasNext = false;
//        if(walkIdList.size() > pageSize){
//            walkIdList.remove(pageSize);
//            hasNext = true;
//        }
//
//        return new SliceImpl<Long>(walkIdList, pageable, hasNext);
//    }

    private NumberExpression<Double> isRouteInDistance(double latCenter, double lonCenter){
        final double KM_PER_LAT = 111.2D;
        final double KM_PER_LON = 89.85D;
        final double ROUND_TO_3 = 1000D;

        NumberExpression<Double> latRoute = route.latitude;
        NumberExpression<Double> lonRoute = route.longitude;

        NumberExpression<Double> latDistance = latRoute.subtract(latCenter).abs().multiply(KM_PER_LAT);
        NumberExpression<Double> lonDistance = lonRoute.subtract(lonCenter).abs().multiply(KM_PER_LON);
        NumberExpression<Double> distance =
                MathExpressions.power(latDistance, 2)
                .add(MathExpressions.power(lonDistance, 2))
                .sqrt()
                .multiply(ROUND_TO_3)
                .round()
                .divide(ROUND_TO_3);

        return distance;
    }
}
