package social.connectus.userservice.domain.port.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import social.connectus.userservice.domain.port.inbound.command.UserPositionCommand;
@FeignClient(name = "spot-service")
public interface SpotClient {
	@PostMapping("/spot/insert")
	void insertPostPosition(@RequestBody UserPositionCommand postPositionCommand);
}
