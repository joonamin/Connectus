package social.connectus.location.infrastructure.mariadb;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import social.connectus.location.application.rest.request.SpotDto;
import social.connectus.location.common.type.PingType;
import social.connectus.location.common.type.Position;
import social.connectus.location.domain.command.CreateSpotCommand;
import social.connectus.location.domain.model.Spot;
import social.connectus.location.domain.ports.outbound.EventFeignPort;
import social.connectus.location.domain.ports.outbound.MilvusPort;
import social.connectus.location.infrastructure.mariadb.repository.SpotRepository;

@Component
@RequiredArgsConstructor
public class EventFeignAdapter implements EventFeignPort {
	private final MilvusPort milvusPort;

	@Override
	@Transactional
	public List<Long> saveAllPositions(List<Position> positions, Long eventId) {
		List<Spot> spots = positions.stream().map(position -> {
			return Spot.builder().longitude(position.getLongitude())
				.latitude(position.getLatitude())
				.type(PingType.EVENT)
				.domainId(eventId)
				.build();
		}).toList();

		List<SpotDto> dto = spots.stream().map(spot -> {
			return SpotDto.builder().latitude(spot.getLatitude())
				.longitude(spot.getLongitude())
				.domainId(spot.getDomainId())
				.type(PingType.EVENT)
				.build();
		}).toList();
		return milvusPort.insertAll(new CreateSpotCommand(dto));
	}
}
