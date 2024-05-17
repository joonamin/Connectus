package social.connectus.location.domain.services;

import java.util.List;

import lombok.RequiredArgsConstructor;
import social.connectus.location.common.customannotations.UseCase;
import social.connectus.location.common.type.Position;
import social.connectus.location.domain.model.Spot;
import social.connectus.location.domain.ports.inbound.EventFeignUseCase;
import social.connectus.location.domain.ports.outbound.EventFeignPort;

@RequiredArgsConstructor
@UseCase
public class EventFeignService implements EventFeignUseCase {

	private final EventFeignPort eventFeignPort;

	@Override
	public List<Long> saveAllPositions(List<Position> positions, Long eventId) {
		return eventFeignPort.saveAllPositions(positions, eventId);
	}
}
