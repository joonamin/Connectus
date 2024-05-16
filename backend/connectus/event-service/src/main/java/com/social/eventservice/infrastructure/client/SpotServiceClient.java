package com.social.eventservice.infrastructure.client;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.social.eventservice.common.exception.NotFoundException;
import com.social.eventservice.common.exception.SavePositionException;
import com.social.eventservice.common.type.Position;
import com.social.eventservice.common.type.Spot;

// FeignClient 끼리는 api gateway를 경유하지 않고 직접 요청을 보낸다.
@FeignClient(name = "spot-service", url = "${external.spot-service}")
public interface SpotServiceClient {
	@PostMapping("/spot")
	List<Spot> saveAllPositions(@RequestBody List<Position> positions) throws SavePositionException;

	@GetMapping("/spot/{id}")
	Optional<Spot> getSpotById(@PathVariable(value = "id") Long id) throws NotFoundException;

	@PutMapping("/spot/{spotId}")
	void updateEventId(@PathVariable Long spotId, @RequestParam Long domainId, @RequestParam String type);
}
