package com.social.eventservice.infrastructure.client;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.social.eventservice.common.exception.NotFoundException;
import com.social.eventservice.common.exception.SavePositionException;
import com.social.eventservice.common.type.Position;
import com.social.eventservice.common.type.Spot;

@FeignClient(name = "spot-service", url = "http://localhost:8080/spot")
public interface SpotServiceClient {
	@PostMapping("/")
	List<Spot> saveAllPositions(@RequestBody List<Position> positions) throws SavePositionException;

	@GetMapping("/{id}")
	Optional<Spot> getSpotById(@PathVariable(value = "id") Long id) throws NotFoundException;
}
