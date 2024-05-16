package social.connectus.location.infrastructure.mariadb;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import social.connectus.location.common.type.PingType;
import social.connectus.location.common.type.Position;
import social.connectus.location.domain.model.Spot;
import social.connectus.location.domain.ports.outbound.EventFeignPort;
import social.connectus.location.infrastructure.mariadb.repository.SpotRepository;

@Component
@RequiredArgsConstructor
public class EventFeignAdapter implements EventFeignPort {
	private final SpotRepository spotRepository;

	@Override
	@Transactional
	public List<Spot> saveAllPositions(List<Position> positions) {
		List<Spot> spots = positions.stream().map(position -> {
			return Spot.builder().longitude(position.getLongitude())
				.latitude(position.getLatitude())
				.type(PingType.EVENT)
				.build();
		}).toList();

		return spotRepository.saveAll(spots);
	}
}
