package social.connectus.userservice.domain.port.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import social.connectus.userservice.domain.port.client.response.MyWalkRecordResponse;
import social.connectus.userservice.domain.port.client.response.WalkRouteResponse;

@FeignClient(name = "walk-service")
public interface WalkClient {
	// 내 산책 기록을 조회하기 위함
	@GetMapping("/walk")
	List<MyWalkRecordResponse> getWalkRecord(@RequestParam Long userId);

	// 내가 좋아요 한, 모든 산책들의 id를 조회한다.
	@GetMapping("/walk/me/like")
	List<Long> getMyPreferenceWalk(@RequestParam Long userId);

	// 특정 산책 기록에 대한 Route 정보를 가져온다.
	@GetMapping("/walk/{id}")
	List<WalkRouteResponse> getWalkRoute(@PathVariable Long walkId);
}
