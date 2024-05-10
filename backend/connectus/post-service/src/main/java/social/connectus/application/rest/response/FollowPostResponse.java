package social.connectus.application.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FollowPostResponse {
	private Long postId;
	private Double longitude;
	private Double latitude;
}
