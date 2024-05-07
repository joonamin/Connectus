package social.connectus.infrastructure.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import social.connectus.application.rest.request.CoordinateRequestDto;

@FeignClient(name = "location-service")
public interface LocationServiceClient {
	@GetMapping("/location-service/{locationId}/coordinate")
	CoordinateRequestDto getPostLocation(@PathVariable Long postId);
}
