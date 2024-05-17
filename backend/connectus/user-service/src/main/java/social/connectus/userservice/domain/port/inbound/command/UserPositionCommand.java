package social.connectus.userservice.domain.port.inbound.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.userservice.domain.application.request.UserPositionRequest;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPositionCommand {
	private Long userId;
	private Double longitude;
	private Double latitude;
	private String type;

	public static UserPositionCommand from(UserPositionRequest request) {
		return UserPositionCommand.builder()
			.userId(request.getUserId())
			.longitude(request.getLongitude())
			.latitude(request.getLatitude())
			.type("USER")
			.build();
	}
}
