package com.social.eventservice.domain.port.inbound;

import java.util.List;

import com.social.eventservice.application.rest.request.MakeEventRequest;
import com.social.eventservice.application.rest.response.EventDetailsDto;
import com.social.eventservice.common.exception.FailedToMakeEvent;
import com.social.eventservice.common.type.Pings;

public interface EventUseCase {
	void makeEvent(MakeEventRequest request) throws FailedToMakeEvent;
	EventDetailsDto detailsEvent(Long eventId);
	List<Pings> spreadPings(Long userId, Long eventId);
}
