package social.connectus.infrastructure.databases;

import org.springframework.stereotype.Repository;

import static social.connectus.domain.model.QLikes.*;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import social.connectus.common.type.Type;

@RequiredArgsConstructor
@Repository
public class LikeRepositoryCustomImpl implements LikeRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Long countByDomainIdAndType(Long domainId, Type type) {

		return jpaQueryFactory.select(likes.count())
			.from(likes)
			.where(likes.domainId.eq(domainId).and(likes.type.eq(type)))
			.fetchOne();
	}
}
