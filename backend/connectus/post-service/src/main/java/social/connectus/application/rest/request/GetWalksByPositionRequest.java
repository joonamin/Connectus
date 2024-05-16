package social.connectus.application.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetWalksByPositionRequest {
	private double latitude;
	private double longitude;
	private double kmRadius;
	private long userId;
	private int pageNumber;
	private int pageSize;

	public static GetWalksByPositionRequest from(
		CoordinateRequestDto userPosition, int pageNum, int pageSize, Long userId, Double kmRadius) {
		return GetWalksByPositionRequest.builder()
			.userId(userId)
			.latitude(userPosition.getLatitude())
			.longitude(userPosition.getLongitude())
			.kmRadius(kmRadius)
			.pageNumber(pageNum)
			.pageSize(pageSize)
			.build();
	}
}
