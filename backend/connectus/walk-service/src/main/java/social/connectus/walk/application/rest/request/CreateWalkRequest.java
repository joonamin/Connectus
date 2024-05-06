package social.connectus.walk.application.rest.request;

import jakarta.persistence.*;
import lombok.*;
import social.connectus.walk.domain.model.VO.Route;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateWalkRequest {
    private Long userId;
    private String title;
    private Route route;

    @Column(nullable = false)
    private int likeCount;

    @Column(nullable = false)
    private List<Long> completedAchievement;

    @Column(nullable = false)
    private Long participateEvent;

    @Column(nullable = false)
    private List<Long> postList;
}
