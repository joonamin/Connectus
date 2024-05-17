package com.social.eventservice.domain.services;

import java.io.IOException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.social.eventservice.application.rest.request.MakeEventRequest;
import com.social.eventservice.application.rest.response.EventDetailsResponse;
import com.social.eventservice.application.rest.response.PingsDetailsResponse;
import com.social.eventservice.common.annotation.UseCase;
import com.social.eventservice.common.type.Spot;
import com.social.eventservice.common.utils.converter.MapperUtil;
import com.social.eventservice.common.utils.converter.command.meta.MakeEventCommandMetadata;
import com.social.eventservice.domain.dto.InitSpotInfoCommand;
import com.social.eventservice.domain.dto.MakeEventCommand;
import com.social.eventservice.domain.model.Event;
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

	// todo: feign client 요청이 하나라도 실패할 경우, SAGA 패턴을 적용하여 롤백 이벤트 발행후에 처리
	@Override
	@Transactional
	public void makeEvent(MakeEventRequest request) throws IOException {
		String imageUrl = imagePort.uploadImage(request.getImage());

		MakeEventCommand command = mapperUtil.requestToCommand(request);
		command.setImageUrl(imageUrl);

		Event event = eventPort.makeEvent(command);
		List<Long> spotIdList = spotPort.saveAllPositions(request.getPositions(), event.getId());
		// 이벤트를 먼저 만들고, 해당 eventId를 이용하여 spotId를 저장한다.
		// 이 후, event의 spotIdList를 저장한다.
		event.setSpotIdList(spotIdList);
		eventPort.initSpotInfo(event.getId(), InitSpotInfoCommand.builder()
			.spotIdList(spotIdList).build());
	}

	@Override
	public EventDetailsResponse detailsEvent(Long eventId) {
		return eventPort.detailsEvent(eventId);
	}

	@Override
	public List<PingsDetailsResponse> spreadPings(Long userId, Long eventId) {
		// 유저가 달성하지 않은 ping에 대해서만 ping을 뿌려준다.
		return eventPort.spreadPings(userId, eventId);
	}
}
