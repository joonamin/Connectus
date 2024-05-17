package com.social.eventservice.domain.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer reward;

	private Boolean isFinished;

	private String imageUrl;

	private Long hostId; // 이벤트 주최자의 id

	private String title;

	private String description;

	@ElementCollection(fetch = FetchType.LAZY)
	private List<Long> spotIdList;

	@ElementCollection(fetch = FetchType.LAZY)
	private List<Long> onClearUserIdList;

}
