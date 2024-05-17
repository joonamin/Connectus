package social.connectus.application.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import social.connectus.application.rest.request.CoordinateRequestDto;

@AllArgsConstructor
@Getter
@Builder
public class FollowPostResponse {
	private Long postId;
	private Double longitude;
	private Double latitude;

	public static FollowPostResponse from(CoordinateRequestDto dto, Long postId) {
		return FollowPostResponse.builder()
			.postId(postId)
			.longitude(dto.getLongitude())
			.latitude(dto.getLatitude())
			.build();
	}
}
