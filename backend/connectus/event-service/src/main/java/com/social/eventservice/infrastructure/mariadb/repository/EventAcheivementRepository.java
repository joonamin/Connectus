package com.social.eventservice.infrastructure.mariadb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.eventservice.domain.model.EventAchievement;

@Repository
public interface EventAcheivementRepository extends JpaRepository<EventAchievement, Long>, EventAcheivementRepositoryCustom {
}
