package com.social.eventservice.infrastructure.mariadb;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.social.eventservice.application.rest.request.MakeEventRequest;
import com.social.eventservice.application.rest.response.EventDetailsDto;
import com.social.eventservice.common.type.Pings;
import com.social.eventservice.common.type.Spot;
import com.social.eventservice.domain.dto.MakeEventCommand;
import com.social.eventservice.domain.model.Event;
import com.social.eventservice.domain.port.outbound.EventPort;
import com.social.eventservice.infrastructure.external.FileAdapter;
import com.social.eventservice.infrastructure.mariadb.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EventAdapter implements EventPort {

	private final EventRepository eventRepository;
	private final ModelMapper modelMapper;
	private final FileAdapter fileAdapter;

	@Override
	@Transactional
	public void makeEvent(MakeEventCommand request) {

		// 지정된 spot을 저장한 다음, 저장된 아이디들을 받아오는 리스트를 만든다
		// 해당 로직을 service단에서 해야할 지 걱정이긴하다.
		// service단에서, spot service를 호출하여 해당 정보들을 저장한 다음,
		// 가져오는 형식으로 구현하는 것이 좋을 것 같다.
		// service 단에서 처리하는걸로 ㅎ.ㅎ
		List<Long> spotIdList = request.getSpotList().stream().map(Spot::getId).toList();
		Event event = Event.builder()
			.reward(request.getReward())
			.isFinished(false)
			.imageUrl(request.getImageUrl())
			.hostId(request.getUserId())
			.title(request.getTitle())
			.description(request.getDescription())
			.spotIdList(spotIdList).build();
		eventRepository.save(event);
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
