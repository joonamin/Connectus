package social.connectus.infrastructure.databases.mariadb.repository;

import org.springframework.stereotype.Repository;
import static social.connectus.domain.model.RDBMS.QPost.*;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import social.connectus.domain.model.RDBMS.Post;

@Repository
@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom{
	private final JPAQueryFactory jpaQueryFactory;
	@Override
	public List<Post> findByIdList(List<Long> postIdList) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		for(Long id : postIdList) {
			booleanBuilder.or(post.id.eq(id));
		}
		return jpaQueryFactory.selectFrom(post)
			.where(booleanBuilder)
			.fetch();
	}

	@Override
	public List<Post> findByWalkId(Long walkId) {
		return jpaQueryFactory.selectFrom(post)
			.where(post.walkId.eq(walkId))
			.fetch();
	}

	@Override
	public List<Post> findByWalkIdList(List<Long> walkId) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		for(Long id : walkId) {
			booleanBuilder.or(post.walkId.eq(id));
		}
		return jpaQueryFactory.selectFrom(post)
			.where(booleanBuilder)
			.fetch();
	}

}
