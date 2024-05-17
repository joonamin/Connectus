package social.connectus.location.domain.ports.outbound;

import java.util.List;

import social.connectus.location.common.type.Position;

public interface EventFeignPort {
	List<Long> saveAllPositions(List<Position> positions, Long eventId);
}
