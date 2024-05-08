package social.connectus.walk.domain.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWalk is a Querydsl query type for Walk
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWalk extends EntityPathBase<Walk> {

    private static final long serialVersionUID = 186875843L;

    public static final QWalk walk = new QWalk("walk");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final SetPath<Long, NumberPath<Long>> completedAchievement = this.<Long, NumberPath<Long>>createSet("completedAchievement", Long.class, NumberPath.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final SetPath<Long, NumberPath<Long>> likeUsers = this.<Long, NumberPath<Long>>createSet("likeUsers", Long.class, NumberPath.class, PathInits.DIRECT2);

    public final NumberPath<Long> participateEvent = createNumber("participateEvent", Long.class);

    public final ListPath<social.connectus.walk.domain.model.VO.Position, social.connectus.walk.domain.model.VO.QPosition> route = this.<social.connectus.walk.domain.model.VO.Position, social.connectus.walk.domain.model.VO.QPosition>createList("route", social.connectus.walk.domain.model.VO.Position.class, social.connectus.walk.domain.model.VO.QPosition.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final NumberPath<Integer> walkDistance = createNumber("walkDistance", Integer.class);

    public final NumberPath<Long> walkId = createNumber("walkId", Long.class);

    public final NumberPath<Integer> walkTime = createNumber("walkTime", Integer.class);

    public QWalk(String variable) {
        super(Walk.class, forVariable(variable));
    }

    public QWalk(Path<? extends Walk> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWalk(PathMetadata metadata) {
        super(Walk.class, metadata);
    }

}

