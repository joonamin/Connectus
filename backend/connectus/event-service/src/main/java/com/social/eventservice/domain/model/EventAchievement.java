package com.social.eventservice.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class EventAchievement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	Long userId;
	Long pingId;
	Long eventId;
}
