package com.social.eventservice.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.social.eventservice.common.type.Spot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MakeEventCommand {
	private Long userId;
	private String title;
	private String imageUrl;
	private String description;
	private List<Spot> spotList;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private int reward;
}
