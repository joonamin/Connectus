package social.connectus.location.domain.ports.outbound;

import java.util.List;

import social.connectus.location.common.type.Position;
import social.connectus.location.domain.model.Spot;

public interface EventFeignPort {
	List<Spot> saveAllPositions(List<Position> positions);
}
