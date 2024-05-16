package social.connectus.location.application.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import social.connectus.location.application.rest.response.EventSpotResponse;
import social.connectus.location.common.type.Position;
import social.connectus.location.domain.model.Spot;
import social.connectus.location.domain.ports.inbound.EventFeignUseCase;
import social.connectus.location.domain.ports.inbound.SpotUseCase;

@RestController
@RequestMapping("/spot")
@RequiredArgsConstructor
public class EventFeignController {

	private final EventFeignUseCase eventFeignUseCase;

	@PostMapping("/")
	List<EventSpotResponse> saveAllPositions(@RequestBody List<Position> positions) {
		List<Spot> spots = eventFeignUseCase.saveAllPositions(positions);
		return spots.stream()
			.map(spot -> EventSpotResponse.builder()
				.id(spot.getId())
				.longitude(spot.getLongitude())
				.latitude(spot.getLatitude())
				.build()).toList();
	}

}
