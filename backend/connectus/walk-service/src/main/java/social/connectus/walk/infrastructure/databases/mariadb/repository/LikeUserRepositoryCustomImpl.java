package social.connectus.walk.infrastructure.databases.mariadb.repository;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;
import static social.connectus.walk.domain.model.entity.QLikeUser.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LikeUserRepositoryCustomImpl implements LikeUserRepositoryCustom{
	private final JPAQueryFactory jpaQueryFactory;
	@Override
	public List<Long> findAllByUserId(Long userId) {
		return jpaQueryFactory.select(likeUser.walk.id)
			.from(likeUser)
			.where(likeUser.userId.eq(userId))
			.groupBy(likeUser.walk.id)
			.fetch();
	}
}
