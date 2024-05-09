package social.connectus.userservice.domain.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 2098114514L;

    public static final QUser user = new QUser("user");

    public final ListPath<social.connectus.userservice.common.type.Achievement, EnumPath<social.connectus.userservice.common.type.Achievement>> accomplishedAchievements = this.<social.connectus.userservice.common.type.Achievement, EnumPath<social.connectus.userservice.common.type.Achievement>>createList("accomplishedAchievements", social.connectus.userservice.common.type.Achievement.class, EnumPath.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> birthday = createDate("birthday", java.time.LocalDate.class);

    public final ListPath<Long, NumberPath<Long>> chatRoomIds = this.<Long, NumberPath<Long>>createList("chatRoomIds", Long.class, NumberPath.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final NumberPath<Integer> postCount = createNumber("postCount", Integer.class);

    public final ListPath<Long, NumberPath<Long>> postHistory = this.<Long, NumberPath<Long>>createList("postHistory", Long.class, NumberPath.class, PathInits.DIRECT2);

    public final StringPath profileImageUrl = createString("profileImageUrl");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final NumberPath<Integer> walkCount = createNumber("walkCount", Integer.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

