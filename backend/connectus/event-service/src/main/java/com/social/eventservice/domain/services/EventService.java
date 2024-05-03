package com.social.eventservice.domain.services;

import java.io.IOException;
import java.util.List;

import com.social.eventservice.application.rest.request.MakeEventRequest;
import com.social.eventservice.application.rest.response.EventDetailsResponse;
import com.social.eventservice.common.annotation.UseCase;
import com.social.eventservice.common.type.Ping;
import com.social.eventservice.common.type.Spot;
import com.social.eventservice.common.utils.converter.MapperUtil;
import com.social.eventservice.common.utils.converter.command.meta.MakeEventCommandMetadata;
import com.social.eventservice.domain.dto.MakeEventCommand;
import com.social.eventservice.domain.port.inbound.EventUseCase;
import com.social.eventservice.domain.port.outbound.EventPort;
import com.social.eventservice.domain.port.outbound.ImagePort;
import com.social.eventservice.domain.port.outbound.SpotPort;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class EventService implements EventUseCase {

	private final EventPort eventPort;
	private final SpotPort spotPort;
	private final ImagePort imagePort;
	private final MapperUtil mapperUtil;

	@Override
	public void makeEvent(MakeEventRequest request) throws IOException {
		List<Spot> spotList = spotPort.saveAllPositions(request.getPositions());
		String imageUrl = imagePort.uploadImage(request.getImage());
		MakeEventCommandMetadata meta = MakeEventCommandMetadata.of(spotList, imageUrl);
		MakeEventCommand command = mapperUtil.requestToCommand(request, meta);
		eventPort.makeEvent(command);
	}

	@Override
	public EventDetailsResponse detailsEvent(Long eventId) {
		return eventPort.detailsEvent(eventId);
	}

	@Override
	public List<Ping> spreadPings(Long userId, Long eventId) {
		// 유저가 달성하지 않은 ping에 대해서만 ping을 뿌려준다.
		return eventPort.spreadPings(userId, eventId);
	}
}
