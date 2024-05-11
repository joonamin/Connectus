package social.connectus.infrastructure.databases.mariadb.repository;

import org.springframework.stereotype.Repository;
import static social.connectus.domain.model.RDBMS.QComment.*;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom{
	// private final JPAQueryFactory jpaQueryFactory;
	// @Override
	// public Long commentCountByPostId(Long postId) {
	// 	return jpaQueryFactory.selectFrom(comment)
	// 		.where(comment.post.id.eq(postId))
	// 		.stream().count();
	// }
}
