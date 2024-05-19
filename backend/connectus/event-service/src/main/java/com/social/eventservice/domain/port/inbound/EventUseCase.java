package com.social.eventservice.domain.port.inbound;

import java.io.IOException;
import java.util.List;

import com.social.eventservice.application.rest.request.MakeEventRequest;
import com.social.eventservice.application.rest.response.EventDetailsResponse;
import com.social.eventservice.application.rest.response.PingsDetailsResponse;
import com.social.eventservice.common.exception.FailedToMakeEvent;
import com.social.eventservice.domain.model.Event;

public interface EventUseCase {
	void makeEvent(MakeEventRequest request) throws FailedToMakeEvent, IOException;
	EventDetailsResponse detailsEvent(Long eventId);
	List<PingsDetailsResponse> spreadPings(Long userId, Long eventId);

    List<Event> getOngoingEventList();

	Event finishEvent(Long eventId);
}
