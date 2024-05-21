package com.social.eventservice.application.rest.response;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.social.eventservice.domain.model.Event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDetailsResponse {

	private Long id;

	private Integer reward;

	private Boolean isFinished;

	private String imageUrl;

	private Long hostId; // 이벤트 주최자의 id

	private String title;

	private String description;

	private List<Long> spotIdList;

	public static EventDetailsResponse from(Event event) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(event, EventDetailsResponse.class);
	}
}
