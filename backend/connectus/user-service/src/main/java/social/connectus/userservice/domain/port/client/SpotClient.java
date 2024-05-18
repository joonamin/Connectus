package social.connectus.userservice.domain.port.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import social.connectus.userservice.domain.port.inbound.command.SpotIdListDto;
import social.connectus.userservice.domain.port.inbound.command.CreateUserPositionCommand;
import social.connectus.userservice.domain.port.inbound.command.UpdateUserPositionCommand;

@FeignClient(name = "spot-service")
public interface SpotClient {
	@PostMapping("/spot/insert")
	SpotIdListDto insertUserPosition(@RequestBody CreateUserPositionCommand userPositionCommand);

	@PostMapping("/spot/update")
	SpotIdListDto updateUserPosition(@RequestBody UpdateUserPositionCommand userPositionCommand);

	@PostMapping("/spot/delete")
	void deleteUserPosition(@RequestBody SpotIdListDto command);
}
