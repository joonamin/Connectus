package com.social.eventservice.application.rest.response;

import com.social.eventservice.domain.model.Event;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventFinishResponse {
    private Long eventId;
    private Boolean isFinished;

    public static EventFinishResponse from(Event event){
        return EventFinishResponse.builder()
                .eventId(event.getId())
                .isFinished(event.getIsFinished())
                .build();
    }
}
