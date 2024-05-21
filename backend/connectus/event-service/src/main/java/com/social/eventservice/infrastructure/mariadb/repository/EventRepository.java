package com.social.eventservice.infrastructure.mariadb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.eventservice.domain.model.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom {
    List<Event> findByIsFinished(boolean b);
}
