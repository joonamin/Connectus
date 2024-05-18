package social.connectus.infrastructure.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import social.connectus.application.rest.request.OpenPostRequest;
import social.connectus.application.rest.response.OpenedPostResponse;
import social.connectus.application.rest.response.PointResponse;
import social.connectus.application.rest.response.UserInfoResponse;

@FeignClient(name = "user-service")
public interface UserServiceClient {
	@GetMapping("/user/{userId}/openedPosts")
	OpenedPostResponse getOpenedPost(@PathVariable("userId") Long userId);

	@PostMapping("/user/{userId}/openedPost")
	void updateOpenedPost(@PathVariable("userId") Long userId, @RequestBody Long postId);
	@PostMapping("/user/{userId}/openedPosts")
	void updateOpenedPosts(@PathVariable("userId") Long userId, @RequestBody List<Long> postIdList);

	@GetMapping("/user/health-check")
	String healthCheck();

	@GetMapping("/user/{userId}/get-author-info")
	UserInfoResponse getUserInfo(@PathVariable("userId") Long userId);

	@PostMapping("/user/insert/post-history")
	PointResponse insertOpenPost(@RequestBody OpenPostRequest request);
}
