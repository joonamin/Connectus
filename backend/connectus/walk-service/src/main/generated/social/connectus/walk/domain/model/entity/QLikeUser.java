package social.connectus.walk.domain.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeUser is a Querydsl query type for LikeUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikeUser extends EntityPathBase<LikeUser> {

    private static final long serialVersionUID = -122958596L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeUser likeUser = new QLikeUser("likeUser");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QWalk walk;

    public QLikeUser(String variable) {
        this(LikeUser.class, forVariable(variable), INITS);
    }

    public QLikeUser(Path<? extends LikeUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeUser(PathMetadata metadata, PathInits inits) {
        this(LikeUser.class, metadata, inits);
    }

    public QLikeUser(Class<? extends LikeUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.walk = inits.isInitialized("walk") ? new QWalk(forProperty("walk")) : null;
    }

}

