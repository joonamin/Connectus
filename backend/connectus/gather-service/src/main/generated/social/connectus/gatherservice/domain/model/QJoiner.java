package social.connectus.gatherservice.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QJoiner is a Querydsl query type for Joiner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJoiner extends EntityPathBase<Joiner> {

    private static final long serialVersionUID = 537052875L;

    public static final QJoiner joiner = new QJoiner("joiner");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> joinerId = createNumber("joinerId", Long.class);

    //inherited
    public final NumberPath<Long> spotId = _super.spotId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QJoiner(String variable) {
        super(Joiner.class, forVariable(variable));
    }

    public QJoiner(Path<? extends Joiner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJoiner(PathMetadata metadata) {
        super(Joiner.class, metadata);
    }

}

