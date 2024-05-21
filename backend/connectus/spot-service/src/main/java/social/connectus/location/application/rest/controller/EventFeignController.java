package social.connectus.location.application.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import social.connectus.location.common.type.Position;
import social.connectus.location.domain.ports.inbound.EventFeignUseCase;

@RestController
@RequestMapping("/spot")
@RequiredArgsConstructor
public class EventFeignController {

	private final EventFeignUseCase eventFeignUseCase;

	@PostMapping("/")
	List<Long> saveAllPositions(@RequestBody List<Position> positions, @RequestParam Long eventId) {
		return eventFeignUseCase.saveAllPositions(positions, eventId);
	}

}
