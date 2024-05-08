package social.connectus.userservice.domain.application.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RefreshAchievementResponse {
	List<AchievementResponse> completedAchievementInThisRequest;
}
