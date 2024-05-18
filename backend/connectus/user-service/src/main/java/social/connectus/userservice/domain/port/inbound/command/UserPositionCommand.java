package social.connectus.userservice.domain.port.inbound.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.userservice.application.request.UserPositionRequest;
import social.connectus.userservice.domain.model.VO.SpotDto;

import java.util.Arrays;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPositionCommand {
	private List<SpotDto> spotList;
	public static UserPositionCommand from(UserPositionRequest request) {
		return UserPositionCommand.builder()
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
