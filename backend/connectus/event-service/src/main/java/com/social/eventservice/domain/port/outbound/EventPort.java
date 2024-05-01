package com.social.eventservice.domain.port.outbound;

import java.util.List;

import com.social.eventservice.application.rest.response.EventDetailsDto;
import com.social.eventservice.common.type.Pings;
import com.social.eventservice.domain.dto.MakeEventCommand;

public interface EventPort {
	void makeEvent(MakeEventCommand request);
	EventDetailsDto detailsEvent(Long eventId);
	List<Pings> spreadPings(Long userId, Long eventId);

}
