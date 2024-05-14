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

    public final SetPath<CompletedAchievement, QCompletedAchievement> completedAchievement = this.<CompletedAchievement, QCompletedAchievement>createSet("completedAchievement", CompletedAchievement.class, QCompletedAchievement.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isPublic = createBoolean("isPublic");

    public final SetPath<LikeUser, QLikeUser> likeUsers = this.<LikeUser, QLikeUser>createSet("likeUsers", LikeUser.class, QLikeUser.class, PathInits.DIRECT2);

    public final NumberPath<Long> participateEvent = createNumber("participateEvent", Long.class);

    public final ListPath<Post, QPost> postList = this.<Post, QPost>createList("postList", Post.class, QPost.class, PathInits.DIRECT2);

    public final ListPath<Route, QRoute> route = this.<Route, QRoute>createList("route", Route.class, QRoute.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> spotId = _super.spotId;

    public final StringPath title = createString("title");

    public final SetPath<TrackingUser, QTrackingUser> trackingUsers = this.<TrackingUser, QTrackingUser>createSet("trackingUsers", TrackingUser.class, QTrackingUser.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final NumberPath<Double> walkDistance = createNumber("walkDistance", Double.class);

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

