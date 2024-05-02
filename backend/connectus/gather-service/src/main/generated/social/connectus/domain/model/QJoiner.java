package social.connectus.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJoiner is a Querydsl query type for Joiner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJoiner extends EntityPathBase<Joiner> {

    private static final long serialVersionUID = 723548703L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJoiner joiner = new QJoiner("joiner");

    public final QGather gather;

    public final NumberPath<Long> joiner_id = createNumber("joiner_id", Long.class);

    public final NumberPath<Long> user_id = createNumber("user_id", Long.class);

    public QJoiner(String variable) {
        this(Joiner.class, forVariable(variable), INITS);
    }

    public QJoiner(Path<? extends Joiner> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJoiner(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJoiner(PathMetadata metadata, PathInits inits) {
        this(Joiner.class, metadata, inits);
    }

    public QJoiner(Class<? extends Joiner> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gather = inits.isInitialized("gather") ? new QGather(forProperty("gather")) : null;
    }

}

