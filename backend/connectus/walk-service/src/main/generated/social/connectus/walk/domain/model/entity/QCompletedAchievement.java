package social.connectus.walk.domain.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCompletedAchievement is a Querydsl query type for CompletedAchievement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCompletedAchievement extends EntityPathBase<CompletedAchievement> {

    private static final long serialVersionUID = 1987947998L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCompletedAchievement completedAchievement = new QCompletedAchievement("completedAchievement");

    public final NumberPath<Long> achievementId = createNumber("achievementId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QWalk walk;

    public QCompletedAchievement(String variable) {
        this(CompletedAchievement.class, forVariable(variable), INITS);
    }

    public QCompletedAchievement(Path<? extends CompletedAchievement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCompletedAchievement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCompletedAchievement(PathMetadata metadata, PathInits inits) {
        this(CompletedAchievement.class, metadata, inits);
    }

    public QCompletedAchievement(Class<? extends CompletedAchievement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.walk = inits.isInitialized("walk") ? new QWalk(forProperty("walk")) : null;
    }

}

