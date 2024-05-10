package social.connectus.userservice.domain.port.external.port;

import static social.connectus.userservice.domain.application.response.MyPreferenceRouteResponse.*;

import java.util.List;

import social.connectus.userservice.domain.application.response.MyPreferenceRouteResponse;
import social.connectus.userservice.domain.application.response.MyWalkResponse;
import social.connectus.userservice.domain.application.response.MyWalkResponse.WalkRecord;
import social.connectus.userservice.domain.port.client.response.AchievementResponse;

public interface WalkPort {
	List<AchievementResponse> getMyAchievements(Long userId);

	List<WalkRecord> getMyWalkRecords(Long userId);

	// 내가 좋아요 누른 산책의 경로 표시
	List<Position> getMyPreferenceWalkRoutes(Long userId);
}
