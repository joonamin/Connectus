package social.connectus.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGather is a Querydsl query type for Gather
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGather extends EntityPathBase<Gather> {

    private static final long serialVersionUID = 625053891L;

    public static final QGather gather = new QGather("gather");

    public final ListPath<Candidate, QCandidate> candidateList = this.<Candidate, QCandidate>createList("candidateList", Candidate.class, QCandidate.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final StringPath endTime = createString("endTime");

    public final NumberPath<Long> hostId = createNumber("hostId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isClosed = createBoolean("isClosed");

    public final ListPath<Joiner, QJoiner> joinerList = this.<Joiner, QJoiner>createList("joinerList", Joiner.class, QJoiner.class, PathInits.DIRECT2);

    public final NumberPath<Integer> maxJoiner = createNumber("maxJoiner", Integer.class);

    public QGather(String variable) {
        super(Gather.class, forVariable(variable));
    }

    public QGather(Path<? extends Gather> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGather(PathMetadata metadata) {
        super(Gather.class, metadata);
    }

}

