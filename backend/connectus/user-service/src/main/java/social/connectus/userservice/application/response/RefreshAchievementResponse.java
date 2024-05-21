package social.connectus.userservice.application.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RefreshAchievementResponse {
	List<AchievementResponse> completedAchievementInThisRequest;
}
