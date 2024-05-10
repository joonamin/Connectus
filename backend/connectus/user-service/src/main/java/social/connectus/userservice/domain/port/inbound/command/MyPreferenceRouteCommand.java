package social.connectus.userservice.domain.port.inbound.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import social.connectus.userservice.domain.application.request.MyPreferenceRouteRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyPreferenceRouteCommand {
	Long userId;

	public static MyPreferenceRouteCommand from(MyPreferenceRouteRequest request) {
		return MyPreferenceRouteCommand.builder()
			.userId(request.getUserId())
			.build();
	}

}
