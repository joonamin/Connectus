package com.social.eventservice.infrastructure.external;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.social.eventservice.common.exception.SavePositionException;
import com.social.eventservice.common.type.Position;
import com.social.eventservice.common.type.Spot;
import com.social.eventservice.domain.port.outbound.SpotPort;
import com.social.eventservice.infrastructure.client.SpotServiceClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpotAdapter implements SpotPort {

	private final SpotServiceClient spotServiceClient;

	@Override
	public List<Spot> saveAllPositions(List<Position> positions) throws SavePositionException {
		return spotServiceClient.saveAllPositions(positions);
	}

	@Override
	public Optional<Spot> getSpotById(Long id) {
		return spotServiceClient.getSpotById(id);
	}

	@Override
	public void initEventId(Long eventId) {

	}

}
