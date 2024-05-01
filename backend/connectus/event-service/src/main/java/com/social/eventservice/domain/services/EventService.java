package com.social.eventservice.domain.services;

import java.util.List;

import com.social.eventservice.application.rest.request.MakeEventRequest;
import com.social.eventservice.application.rest.response.EventDetailsDto;
import com.social.eventservice.common.annotation.UseCase;
import com.social.eventservice.common.type.Pings;
import com.social.eventservice.common.type.Spot;
import com.social.eventservice.common.utils.converter.MapperUtil;
import com.social.eventservice.common.utils.converter.command.meta.MakeEventCommandMetadata;
import com.social.eventservice.domain.dto.MakeEventCommand;
import com.social.eventservice.domain.port.inbound.EventUseCase;
import com.social.eventservice.domain.port.outbound.EventPort;
import com.social.eventservice.domain.port.outbound.ImagePort;
import com.social.eventservice.domain.port.outbound.SpotPort;

import lombok.RequiredArgsConstructor;

// TODO: to implement this class
@UseCase
@RequiredArgsConstructor
public class EventService implements EventUseCase {

	private final EventPort eventPort;
	private final SpotPort spotPort;
	private final ImagePort imagePort;
	private final MapperUtil mapperUtil;

	@Override
	public void makeEvent(MakeEventRequest request) {
		List<Spot> spotList = spotPort.saveAllPositions(request.getPositions());
		String imageUrl = imagePort.imageUpload(request.getImage());
		MakeEventCommandMetadata meta = MakeEventCommandMetadata.of(spotList, imageUrl);
		MakeEventCommand command = mapperUtil.requestToCommand(request, meta);
		eventPort.makeEvent(command);
	}

	@Override
	public EventDetailsDto detailsEvent(Long eventId) {
		return null;
	}

	@Override
	public List<Pings> spreadPings(Long userId, Long eventId) {
		return null;
	}
}
