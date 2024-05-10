package social.connectus.infrastructure.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.ws.rs.Path;
import social.connectus.application.rest.response.FollowPostResponse;

@FeignClient(name = "location-service")
public interface LocationServiceClient {
	@GetMapping("/location/{postId}/returnPostLocation")
	FollowPostResponse getPostLocation(@PathVariable Long postId, @RequestParam String type);
}
