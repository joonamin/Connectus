package social.connectus.userservice.domain.port.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import social.connectus.userservice.application.response.MyLikePost;
import social.connectus.userservice.domain.port.client.response.PostRecord;

@FeignClient(name = "post-service")
public interface PostClient {

	@GetMapping("/post/{userId}")
	List<PostRecord> getUsersPreferencePost(@PathVariable Long userId);

	@GetMapping("/post/user/{userId}")
	List<Long> getUserListByUserId(@PathVariable Long userId);

	@GetMapping("/post/list")
	List<MyLikePost> getMyLikeList(@RequestParam List<Long> postIdList);
}
