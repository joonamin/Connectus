package social.connectus.infrastructure.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.domain.service.command.PostSpotCommand;

@FeignClient(name = "spot-service")
public interface SpotServiceClient {

	@GetMapping("/spot-service/{locationId}/coordinate")
	CoordinateRequestDto getPostSpot(@PathVariable("postId") Long postId);

	@PostMapping("/spot-service/insert")
	void insertPostSpot(@RequestBody List<PostSpotCommand> postSpotCommand);
}
