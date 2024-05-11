package social.connectus.infrastructure.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "likes-service")
public interface LikesServiceClient {
	@GetMapping("/likes/{postId}/like-count")
	int getLikeCount(@PathVariable Long postId, @RequestParam String typeString);

	@GetMapping("/likes/{postId}/is-like")
	boolean isLike(@PathVariable Long postId, @RequestParam String typeString);
}
