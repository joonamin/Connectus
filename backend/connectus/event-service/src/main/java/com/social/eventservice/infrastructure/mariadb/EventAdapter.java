package com.social.eventservice.infrastructure.mariadb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.social.eventservice.application.rest.response.EventDetailsResponse;
import com.social.eventservice.common.exception.NotFoundException;
import com.social.eventservice.application.rest.response.PingsDetailsResponse;
import com.social.eventservice.common.type.Spot;
import com.social.eventservice.domain.dto.MakeEventCommand;
import com.social.eventservice.domain.model.Event;
import com.social.eventservice.domain.port.outbound.EventAcheivementPort;
import com.social.eventservice.domain.port.outbound.EventPort;
import com.social.eventservice.infrastructure.client.SpotServiceClient;
import com.social.eventservice.infrastructure.mariadb.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EventAdapter implements EventPort {

	private final EventRepository eventRepository;
	private final EventAcheivementPort eventAcheivementPort;
	private final SpotServiceClient spotServiceClient;

	@Override
	@Transactional
	public void makeEvent(MakeEventCommand request) {
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
	public EventDetailsResponse detailsEvent(Long eventId) {
		Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("이벤트를 찾을 수 없습니다"));
		return EventDetailsResponse.from(event);
	}

	@Override
	public List<PingsDetailsResponse> spreadPings(Long userId, Long eventId) {
		// 특정 유저가 참여한 이벤트에 속해있는 핑들 중, 달성하지 않은 ping들을 뿌려준다.
		Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("not found event!"));
		Map<Long, Boolean> uncleared = new HashMap<>();
		event.getSpotIdList().forEach(id -> uncleared.putIfAbsent(id, true));

		List<Long> clearedPingIds = eventAcheivementPort.getClearedPingIds(userId, eventId);
		clearedPingIds.forEach(uncleared::remove);

		List<Long> unclearedPingIds = uncleared.entrySet().stream().filter(Map.Entry::getValue).map(Map.Entry::getKey).toList();

		Function<Long, PingsDetailsResponse> mapper = spotId -> {
			Spot spot = spotServiceClient.getSpotById(spotId)
				.orElseThrow(() -> new NotFoundException("not found spot id"));
			return PingsDetailsResponse.from(spot);
		};

		return unclearedPingIds.stream()
			.map(mapper).toList();
	}

}
