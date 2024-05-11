package social.connectus.walk.application.rest.request;

import lombok.*;
import social.connectus.walk.domain.model.entity.CompletedAchievement;
import social.connectus.walk.domain.model.entity.Post;
import social.connectus.walk.domain.model.entity.Route;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateWalkRequest {
    private Long userId;
    private String title;
    private List<Route> route;
    private Set<CompletedAchievement> completedAchievement;
    private List<Post> postList;
    private int walkTime;
    private double walkDistance;   // 산책 거리
    private Long participateEvent;
    private boolean isPublic;
}
