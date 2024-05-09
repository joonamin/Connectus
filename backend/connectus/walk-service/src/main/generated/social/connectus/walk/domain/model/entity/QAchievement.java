package social.connectus.walk.domain.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAchievement is a Querydsl query type for Achievement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAchievement extends EntityPathBase<Achievement> {

    private static final long serialVersionUID = 927978485L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAchievement achievement = new QAchievement("achievement");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QWalk walk;

    public QAchievement(String variable) {
        this(Achievement.class, forVariable(variable), INITS);
    }

    public QAchievement(Path<? extends Achievement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAchievement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAchievement(PathMetadata metadata, PathInits inits) {
        this(Achievement.class, metadata, inits);
    }

    public QAchievement(Class<? extends Achievement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.walk = inits.isInitialized("walk") ? new QWalk(forProperty("walk")) : null;
    }

}

