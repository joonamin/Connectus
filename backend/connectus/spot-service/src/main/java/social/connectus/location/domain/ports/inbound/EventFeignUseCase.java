package social.connectus.location.domain.ports.inbound;

import java.util.List;

import social.connectus.location.common.type.Position;
import social.connectus.location.domain.model.Spot;

public interface EventFeignUseCase {
	List<Spot> saveAllPositions(List<Position> positions);
}
