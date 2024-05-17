package social.connectus.infrastructure.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.domain.service.command.PostSpotCommand;

@FeignClient(name = "spot-service")
public interface SpotServiceClient {

	@GetMapping("/spot-service/{locationId}/coordinate")
	CoordinateRequestDto getPostSpot(@PathVariable("postId") Long postId, @RequestParam String typeString);

	@PostMapping("/spot/insert")
	List<Long> insertPostSpot(@RequestBody List<PostSpotCommand> postSpotCommand);
}
