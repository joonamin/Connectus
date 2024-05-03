package com.social.eventservice.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEventAchievement is a Querydsl query type for EventAchievement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventAchievement extends EntityPathBase<EventAchievement> {

    private static final long serialVersionUID = 1338866437L;

    public static final QEventAchievement eventAchievement = new QEventAchievement("eventAchievement");

    public final NumberPath<Long> eventId = createNumber("eventId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QEventAchievement(String variable) {
        super(EventAchievement.class, forVariable(variable));
    }

    public QEventAchievement(Path<? extends EventAchievement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventAchievement(PathMetadata metadata) {
        super(EventAchievement.class, metadata);
    }

}

