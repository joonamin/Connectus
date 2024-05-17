package social.connectus.infrastructure.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.request.GetPostSpotRequest;
import social.connectus.application.rest.response.InsertPostSpotResponse;
import social.connectus.domain.service.command.PostSpotCommand;

@FeignClient(name = "spot-service")
public interface SpotServiceClient {

	@PostMapping("/spot/get")
	CoordinateRequestDto getPostSpot(@RequestBody GetPostSpotRequest request);

	@PostMapping("/spot/insert")
	InsertPostSpotResponse insertPostSpot(@RequestBody PostSpotCommand postSpotCommand);
}
