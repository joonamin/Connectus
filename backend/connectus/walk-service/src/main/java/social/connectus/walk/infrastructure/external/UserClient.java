package social.connectus.walk.infrastructure.external;

import org.springframework.web.bind.annotation.*;
import social.connectus.walk.application.rest.response.AchievementIdResponse;
import social.connectus.walk.application.rest.response.AchievementResponse;
import social.connectus.walk.domain.command.GetAchievementsCommand;

import java.util.List;

@org.springframework.cloud.openfeign.FeignClient(name = "user-service")
public interface UserClient {

    @PostMapping("user/refresh-achievement")
    List<AchievementResponse> getAchievementsByWalk(@RequestParam("userId") Long userId, @RequestBody GetAchievementsCommand command);
}
