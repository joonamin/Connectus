package social.connectus.gatherservice.domain.model;

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

    private static final long serialVersionUID = 438558063L;

    public static final QGather gather = new QGather("gather");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final SetPath<Long, NumberPath<Long>> candidateList = this.<Long, NumberPath<Long>>createSet("candidateList", Long.class, NumberPath.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath endTime = createString("endTime");

    public final NumberPath<Long> hostId = createNumber("hostId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isClosed = createBoolean("isClosed");

    public final SetPath<Long, NumberPath<Long>> joinerList = this.<Long, NumberPath<Long>>createSet("joinerList", Long.class, NumberPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> maxJoiner = createNumber("maxJoiner", Integer.class);

    public final NumberPath<Long> spotId = createNumber("spotId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

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

