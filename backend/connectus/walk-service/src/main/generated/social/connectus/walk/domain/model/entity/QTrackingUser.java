package social.connectus.walk.domain.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTrackingUser is a Querydsl query type for TrackingUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrackingUser extends EntityPathBase<TrackingUser> {

    private static final long serialVersionUID = -1522950372L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTrackingUser trackingUser = new QTrackingUser("trackingUser");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final QWalk walk;

    public QTrackingUser(String variable) {
        this(TrackingUser.class, forVariable(variable), INITS);
    }

    public QTrackingUser(Path<? extends TrackingUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTrackingUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTrackingUser(PathMetadata metadata, PathInits inits) {
        this(TrackingUser.class, metadata, inits);
    }

    public QTrackingUser(Class<? extends TrackingUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.walk = inits.isInitialized("walk") ? new QWalk(forProperty("walk")) : null;
    }

}

