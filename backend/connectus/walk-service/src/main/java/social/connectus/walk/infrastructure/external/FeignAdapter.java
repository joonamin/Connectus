package social.connectus.walk.infrastructure.external;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import social.connectus.walk.application.rest.request.CreatePostRequest;
import social.connectus.walk.application.rest.response.AchievementIdResponse;
import social.connectus.walk.application.rest.response.AchievementResponse;
import social.connectus.walk.domain.command.GetAchievementsCommand;
import social.connectus.walk.domain.ports.outbound.FeignPort;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FeignAdapter implements FeignPort {
    private final UserClient userClient;
    private final PostClient postClient;

    public String feignHealthCheck(){
        return userClient.healthCheck();
    }
    @Override
    public List<AchievementResponse> getAchievementsByWalk(Long userId, GetAchievementsCommand command) {
        return userClient.getAchievementsByWalk(userId, command);
    }

    @Override
    public List<Long> createPost(CreatePostRequest request){
        return postClient.createPost(request);
    }
}
