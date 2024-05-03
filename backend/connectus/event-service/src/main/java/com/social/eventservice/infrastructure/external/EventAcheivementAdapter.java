package com.social.eventservice.infrastructure.external;

import java.util.List;

import org.springframework.stereotype.Component;

import com.social.eventservice.common.type.Ping;
import com.social.eventservice.domain.model.EventAchievement;
import com.social.eventservice.domain.port.outbound.EventAcheivementPort;
import com.social.eventservice.infrastructure.mariadb.repository.EventAcheivementRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EventAcheivementAdapter implements EventAcheivementPort {

	private final EventAcheivementRepository eventAcheivementRepository;

	@Override
	public List<Long> getClearedPingIds(Long userId, Long eventId) {
		List<EventAchievement> cleared = eventAcheivementRepository.getClearedEventAchievement(userId,
			eventId);
		return cleared.stream().map(EventAchievement::getPingId).toList();
	}
}
