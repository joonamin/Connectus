package social.connectus.gatherservice.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCandidate is a Querydsl query type for Candidate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCandidate extends EntityPathBase<Candidate> {

    private static final long serialVersionUID = 1586393487L;

    public static final QCandidate candidate = new QCandidate("candidate");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> candidateId = createNumber("candidateId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> spotId = _super.spotId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QCandidate(String variable) {
        super(Candidate.class, forVariable(variable));
    }

    public QCandidate(Path<? extends Candidate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCandidate(PathMetadata metadata) {
        super(Candidate.class, metadata);
    }

}

