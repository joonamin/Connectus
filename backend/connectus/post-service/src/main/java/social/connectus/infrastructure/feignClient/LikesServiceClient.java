package social.connectus.infrastructure.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "likes-service")
public interface LikesServiceClient {
	@GetMapping("/likes-service/{postId}/likeCount")
	int getLikeCount(@PathVariable Long postId);
}
