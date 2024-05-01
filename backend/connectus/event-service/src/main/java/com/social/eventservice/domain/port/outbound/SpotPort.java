package com.social.eventservice.domain.port.outbound;

import java.util.List;

import com.social.eventservice.common.exception.SavePositionException;
import com.social.eventservice.common.type.Position;
import com.social.eventservice.common.type.Spot;

public interface SpotPort {
	List<Spot> saveAllPositions(List<Position> positions) throws SavePositionException;
}
