package social.connectus.location.domain.ports.inbound;

import java.util.List;

import social.connectus.location.common.type.Position;

public interface EventFeignUseCase {
	List<Long> saveAllPositions(List<Position> positions, Long eventId);
}
