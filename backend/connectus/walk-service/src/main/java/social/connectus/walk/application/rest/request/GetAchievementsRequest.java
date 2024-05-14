package social.connectus.walk.application.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.domain.model.entity.Post;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAchievementsRequest {
    private Long userId;
    private Integer postCount;
    private Long participateEvent;
}
