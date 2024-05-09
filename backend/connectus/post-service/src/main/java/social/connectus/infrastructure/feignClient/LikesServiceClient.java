package social.connectus.infrastructure.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "likes-service")
public interface LikesServiceClient {
	@GetMapping("/likes/{postId}/like-count")
	int getLikeCount(@PathVariable Long postId, String type);

	@GetMapping("/likes/{postId}/is-like")
	boolean isLike(@PathVariable Long postId);
}
