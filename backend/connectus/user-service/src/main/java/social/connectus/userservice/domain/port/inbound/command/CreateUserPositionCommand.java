package social.connectus.userservice.domain.port.inbound.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.userservice.application.request.CreateUserPositionRequest;
import social.connectus.userservice.domain.model.VO.SpotDto;

import java.util.Arrays;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserPositionCommand {
	private List<SpotDto> spotList;
	public static CreateUserPositionCommand from(CreateUserPositionRequest request) {
		return CreateUserPositionCommand.builder()
			.spotList(
					Arrays.asList(SpotDto.builder()
									.domainId(request.getUserId())
									.type("USER")
									.latitude(request.getLatitude())
									.longitude(request.getLongitude())
								.build())
			).build();
	}
}
