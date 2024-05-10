package social.connectus.userservice.domain.port.client.response;

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
public class MyWalkRecordResponse {
	private String title;
	private int likeCount;
	private int totalDistance;
	private int	totalTime;
	private int trackingCount;
	private int walkId;
	private int userId;
}
