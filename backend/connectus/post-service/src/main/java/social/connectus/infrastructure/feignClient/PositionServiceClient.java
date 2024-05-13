package social.connectus.infrastructure.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import social.connectus.application.rest.request.CoordinateRequestDto;

@FeignClient(name = "position-service")
public interface 	PositionServiceClient {
	@GetMapping("/position-service/{locationId}/coordinate")
	CoordinateRequestDto getPostPosition(@PathVariable("postId") Long postId);
}
