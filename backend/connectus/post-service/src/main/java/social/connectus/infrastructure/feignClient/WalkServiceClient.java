package social.connectus.infrastructure.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import social.connectus.application.rest.request.CoordinateRequestDto;

@FeignClient(name = "walk-service")
public interface WalkServiceClient {
	@GetMapping("/walk/position")
	Slice<Long> getFeedList(
		@RequestParam("coordinate") CoordinateRequestDto coordinate,
		@RequestParam("pageNum") int pageNum,
		@RequestParam("pageSize") int pageSize,
		@RequestParam("userId") Long userId,
		Double radius
		);
}
