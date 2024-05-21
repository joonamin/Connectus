package social.connectus.infrastructure.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.domain.service.command.PostPositionCommand;

@FeignClient(name = "position-service")
public interface 	PositionServiceClient {
	@GetMapping("/position-service/{locationId}/coordinate")
	CoordinateRequestDto getPostPosition(@PathVariable("postId") Long postId);

	@PostMapping("/position-service/insert")
	void insertPostPosition(@RequestBody List<PostPositionCommand> postPositionCommand);
}
