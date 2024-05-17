package social.connectus.location.application.rest.controller;

import java.util.List;

import org.apache.commons.lang.NullArgumentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import social.connectus.location.application.rest.request.CreateSpotRequest;
import social.connectus.location.application.rest.request.FindNearbyElementRequest;
import social.connectus.location.application.rest.request.GetSpotRequest;
import social.connectus.location.application.rest.request.SpotDto;
import social.connectus.location.application.rest.response.CreateSpotResponse;
import social.connectus.location.application.rest.response.FindNearbyElementResponse;
import social.connectus.location.application.rest.response.GetSpotResponse;
import social.connectus.location.domain.command.CreateSpotCommand;
import social.connectus.location.domain.command.FindNearbyElementCommand;
import social.connectus.location.domain.command.GetSpotCommand;
import social.connectus.location.domain.ports.inbound.SpotUseCase;

@RestController
@RequestMapping("/spot")
@AllArgsConstructor
public class SpotController {

	private final SpotUseCase spotUseCase;

	@GetMapping("/health-check")
	public String health_check() {
		return "It's working on spot-service!";
	}

    @PostMapping("/findNearby")
    public FindNearbyElementResponse findNearbyElement(@RequestBody FindNearbyElementRequest request) {
        FindNearbyElementCommand command = new FindNearbyElementCommand(request.getLatitude(),request.getLongitude());
        return spotUseCase.findNearbyElement(command);
    }

	@PostMapping("/insert")
	public ResponseEntity<CreateSpotResponse> createSpot(@RequestBody CreateSpotRequest request) {
		for (SpotDto spot : request.getSpotList()) {
			if (spot.getLongitude() == null || spot.getLatitude() == null) {
				throw new NullArgumentException("Parameter doesn't allow Null.");
			}
		}
		CreateSpotCommand command = CreateSpotCommand.from(request);
		List<Long> list = spotUseCase.createSpot(command);
		return ResponseEntity.ok(CreateSpotResponse.builder().spotIdList(list).build());
	}

	@PostMapping("/get")
	public ResponseEntity<GetSpotResponse> getSpotList(@RequestBody GetSpotRequest request) {
		GetSpotResponse response = GetSpotResponse.builder()
			.spotList(spotUseCase.getSpot(GetSpotCommand.from(request)))
			.build();
		return ResponseEntity.ok(response);
	}

	@PostMapping("/update")
	public ResponseEntity<String> updateSpotList(@RequestBody CreateSpotRequest request) {
		return ResponseEntity.ok("Not used API.");
		//        CreateSpotResponse response = CreateSpotResponse.builder()
		//                .spotIdList(spotUseCase.updateSpot(CreateSpotCommand.from(request)))
		//                .build();
		//        return ResponseEntity.ok(response);
	}

}
