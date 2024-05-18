package social.connectus.walk.application.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.domain.model.entity.Walk;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyLikeWalkResponse {
	Long walkId;
	Long likeCount;
	int trackingUserCount;
	int walkTime;
	Double walkDistance;
	String imageUrl;

	public static MyLikeWalkResponse from(Walk walk, Long likeCount) {
		return MyLikeWalkResponse.builder()
			.walkId(walk.getId())
			.likeCount(likeCount)
			.trackingUserCount(walk.getTrackingUsers().size())
			.walkDistance(walk.getWalkDistance())
			.walkTime(walk.getWalkTime())
			.imageUrl(walk.getImageUrl())
			.build();
	}
}
