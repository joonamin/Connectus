package social.connectus.userservice.domain.application.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPreferenceRouteResponse {
	private List<Position> route;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Position {
		private double longitude;
		private double latitude;
	}
}
