package social.connectus.userservice.domain.port.external.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import social.connectus.userservice.domain.port.client.AchievementClient;
import social.connectus.userservice.domain.port.client.WalkClient;
import social.connectus.userservice.domain.port.client.response.AchievementResponse;
import social.connectus.userservice.domain.port.client.response.MyWalkRecordResponse;
import social.connectus.userservice.domain.port.external.port.WalkPort;

@Component
@RequiredArgsConstructor
public class WalkAdapter implements WalkPort {

	private final WalkClient walkClient;
	private final AchievementClient achievementClient;

	// 업적 조회
	public List<AchievementResponse> getMyAchievements(Long userId) {
		return achievementClient.getUserAchievements(userId);
	}

	// 내 산책 기록
	public List<MyWalkRecordResponse> getMyWalkRecords(Long userId) {
		return walkClient.getWalkRecord(userId);
	}



}
