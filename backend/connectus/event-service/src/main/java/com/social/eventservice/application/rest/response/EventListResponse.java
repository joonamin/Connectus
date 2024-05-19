package com.social.eventservice.application.rest.response;

import com.social.eventservice.domain.dto.EventDto;
import com.social.eventservice.domain.model.Event;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventListResponse {
    private List<EventDto> eventList;

    public static EventListResponse from(List<Event> eventList) {
        return EventListResponse.builder()
                .eventList(
                        eventList.stream()
                                .map(event -> EventDto.from(event))
                                .toList()
                )
                .build();
    }
}
