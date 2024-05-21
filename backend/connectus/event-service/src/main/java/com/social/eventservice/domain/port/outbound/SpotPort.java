package com.social.eventservice.domain.port.outbound;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.social.eventservice.common.exception.SavePositionException;
import com.social.eventservice.common.type.Position;
import com.social.eventservice.common.type.Spot;

public interface SpotPort {
	List<Long> saveAllPositions(List<Position> positions, Long eventId) throws SavePositionException;
	Optional<Spot> getSpotById(Long id);

	void initEventId(Long spotId, Long eventId);
}
