package social.connectus.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.processing.Generated;

import com.querydsl.core.types.Path;

/**
 * QLikes is a Querydsl query type for Likes
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikes extends EntityPathBase<Likes> {

	private static final long serialVersionUID = 2103220212L;

	public static final QLikes likes = new QLikes("likes");

	public final NumberPath<Long> domainId = createNumber("domainId", Long.class);

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final EnumPath<social.connectus.common.type.Type> type = createEnum("type",
		social.connectus.common.type.Type.class);

	public final NumberPath<Long> userId = createNumber("userId", Long.class);

	public QLikes(String variable) {
		super(Likes.class, forVariable(variable));
	}

	public QLikes(Path<? extends Likes> path) {
		super(path.getType(), path.getMetadata());
	}

	public QLikes(PathMetadata metadata) {
		super(Likes.class, metadata);
	}

}

