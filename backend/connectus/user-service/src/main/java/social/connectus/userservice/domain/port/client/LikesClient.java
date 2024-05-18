package social.connectus.userservice.domain.port.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import social.connectus.userservice.application.response.LikeResponse;

@FeignClient(name = "likes-service")
public interface LikesClient {
	@GetMapping("/likes/list/{userId}")
	List<Long> getUsersPreferencePost(@PathVariable Long userId);
}
