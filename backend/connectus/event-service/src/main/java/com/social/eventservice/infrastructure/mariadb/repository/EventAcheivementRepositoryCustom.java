package com.social.eventservice.infrastructure.mariadb.repository;

import java.util.List;

import com.social.eventservice.domain.model.EventAchievement;

public interface EventAcheivementRepositoryCustom {
	List<EventAchievement> getClearedEventAchievement(Long userId, Long eventId);
}
