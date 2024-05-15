package com.social.eventservice.domain.port.outbound;

import java.util.List;

public interface EventAcheivementPort {
	List<Long> getClearedPingIds(Long userId, Long eventId);
}
