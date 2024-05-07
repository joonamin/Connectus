package social.connectus.infrastructure.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import social.connectus.application.rest.response.OpenedPostResponse;

@FeignClient(name = "user-service")
public interface UserServiceClient {
	@GetMapping("/user-service/{userId}/openedPosts")
	OpenedPostResponse getOpenedPost(@PathVariable Long userId);
}
