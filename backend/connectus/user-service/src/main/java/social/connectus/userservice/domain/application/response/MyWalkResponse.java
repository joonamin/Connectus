package social.connectus.userservice.domain.application.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class MyWalkResponse {

	List<WalkRecord> walkRecords;

	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class WalkRecord {
		Long walkId;
		int totalTime;
		int totalDistance;

		int likeCount;
		int trackingCount;

		String title;
	}

}
