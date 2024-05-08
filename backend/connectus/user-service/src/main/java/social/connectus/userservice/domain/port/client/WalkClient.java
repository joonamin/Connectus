package social.connectus.userservice.domain.port.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import social.connectus.userservice.domain.port.client.response.MyWalkRecordResponse;

@FeignClient(name = "walk-service")
public interface WalkClient {
	// 내 산책 기록을 조회하기 위함
	@GetMapping("/walk")
	List<MyWalkRecordResponse> getWalkRecord(@RequestParam Long userId);
}
