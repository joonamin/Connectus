package com.social.eventservice.infrastructure.external;

import java.util.List;

import org.springframework.stereotype.Component;

import com.social.eventservice.common.exception.SavePositionException;
import com.social.eventservice.common.type.Position;
import com.social.eventservice.common.type.Spot;
import com.social.eventservice.domain.port.outbound.SpotPort;

@Component
public class SpotAdapter implements SpotPort {


	@Override
	public List<Spot> saveAllPositions(List<Position> positions) throws SavePositionException {
		// todo: 외부 서비스와 통신을 하여 Spot entity를 가져온다.
		return null;
	}
}
