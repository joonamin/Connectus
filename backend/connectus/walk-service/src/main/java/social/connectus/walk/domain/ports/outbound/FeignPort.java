package social.connectus.walk.domain.ports.outbound;

import social.connectus.walk.application.rest.request.CreatePostRequest;
import social.connectus.walk.application.rest.response.AchievementIdResponse;
import social.connectus.walk.application.rest.response.AchievementResponse;
import social.connectus.walk.domain.command.GetAchievementsCommand;

import java.util.List;

public interface FeignPort {
    List<AchievementResponse> getAchievementsByWalk(Long userId, GetAchievementsCommand command);

    List<Long> createPost(CreatePostRequest request);
}
