package social.connectus.userservice.domain.port.client.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import social.connectus.userservice.application.response.MyPreferenceRouteResponse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalkRouteResponse {

	private List<MyPreferenceRouteResponse.Position> route;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Position {
		private double longitude;
		private double latitude;
	}

}
