package social.connectus.userservice.domain.port.external.port;

import java.util.List;

import social.connectus.userservice.application.response.MyPreferenceRouteResponse;
import social.connectus.userservice.domain.port.client.response.AchievementResponse;
import social.connectus.userservice.domain.port.client.response.MyWalkRecordResponse;

public interface WalkPort {
	List<AchievementResponse> getMyAchievements(Long userId);

	List<MyWalkRecordResponse> getMyWalkRecords(Long userId);

	// 내가 좋아요 누른 산책의 경로 표시
	List<MyPreferenceRouteResponse.Position> getMyPreferenceWalkRoutes(Long userId);
}
