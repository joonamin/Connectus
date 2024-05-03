package com.social.eventservice.infrastructure.mariadb.repository;

import static com.social.eventservice.domain.model.QEventAchievement.*;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.social.eventservice.domain.model.EventAchievement;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EventAcheivementRepositoryCustomImpl implements EventAcheivementRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<EventAchievement> getClearedEventAchievement(Long userId, Long eventId) {

		return jpaQueryFactory.selectFrom(eventAchievement)
			.where(eventAchievement.eventId.eq(eventId).and(eventAchievement.userId.eq(userId)))
			.fetch();
	}

}
