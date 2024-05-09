package social.connectus.infrastructure.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import social.connectus.application.rest.request.CoordinateRequestDto;

@FeignClient(name = "walk-service")
public interface WalkServiceClient {
	@GetMapping("/walk/feed-list")
	Slice<Long> getFeedList(
		@RequestParam CoordinateRequestDto coordinate,
		@RequestParam int pageNum,
		@RequestParam int pageSize,
		@RequestParam Long userId,
		Double radius
		);
}
