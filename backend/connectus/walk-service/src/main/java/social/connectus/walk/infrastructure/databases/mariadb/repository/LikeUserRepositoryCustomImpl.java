package social.connectus.walk.infrastructure.databases.mariadb.repository;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;
import static social.connectus.walk.domain.model.entity.QLikeUser.*;
import lombok.RequiredArgsConstructor;
import social.connectus.walk.domain.model.entity.Walk;

@RequiredArgsConstructor
public class LikeUserRepositoryCustomImpl implements LikeUserRepositoryCustom{
	private final JPAQueryFactory jpaQueryFactory;
	@Override
	public List<Walk> findAllByUserId(Long userId) {
		return jpaQueryFactory.select(likeUser.walk)
			.from(likeUser)
			.where(likeUser.userId.eq(userId))
			.groupBy(likeUser.walk.id)
			.fetch();
	}

	@Override
	public Long countByWalkId(Long walkId) {
		return jpaQueryFactory.select(likeUser.count())
			.from(likeUser)
			.where(likeUser.walk.id.eq(walkId))
			.groupBy(likeUser.userId)
			.fetchOne();
	}
}
