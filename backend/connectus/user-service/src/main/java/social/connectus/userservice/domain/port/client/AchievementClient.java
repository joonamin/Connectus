package social.connectus.userservice.domain.port.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import social.connectus.userservice.domain.port.client.response.AchievementResponse;

@FeignClient(name = "achievement-service")
public interface AchievementClient {
	@GetMapping("/achievement")
	List<AchievementResponse> getUserAchievements(@RequestParam Long userId);
}
