package social.connectus.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCandidate is a Querydsl query type for Candidate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCandidate extends EntityPathBase<Candidate> {

    private static final long serialVersionUID = -204075589L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCandidate candidate = new QCandidate("candidate");

    public final QGather gather;

    public final NumberPath<Long> user_id = createNumber("user_id", Long.class);

    public QCandidate(String variable) {
        this(Candidate.class, forVariable(variable), INITS);
    }

    public QCandidate(Path<? extends Candidate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCandidate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCandidate(PathMetadata metadata, PathInits inits) {
        this(Candidate.class, metadata, inits);
    }

    public QCandidate(Class<? extends Candidate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gather = inits.isInitialized("gather") ? new QGather(forProperty("gather")) : null;
    }

}

