package social.connectus.infrastructure.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import social.connectus.application.rest.response.OpenedPostResponse;

@FeignClient(name = "user-service")
public interface UserServiceClient {
	@GetMapping("/user/{userId}/openedPosts")
	OpenedPostResponse getOpenedPost(@PathVariable("userId") Long userId);

	@PostMapping("/user/{userId}/openedPosts")
	void updateOpenedPost(@PathVariable("userId") Long userId, @RequestBody Long postId);

	@GetMapping("/user/health-check")
	String healthCheck();

	@GetMapping("/user/{userId}/get-author-name")
	String getAuthorName(@PathVariable("userId") Long userId);
}
