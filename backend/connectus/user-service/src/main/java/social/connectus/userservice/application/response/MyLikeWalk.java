package social.connectus.userservice.application.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyLikeWalk{
	Long walkId;
	Long likeCount;
	int trackingUserCount;
	int walkTime;
	String imageUrl;
	Double walkDistance;
}
