package social.connectus.walk.domain.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import social.connectus.walk.domain.model.VO.PostOnWalk;
import social.connectus.walk.domain.model.VO.Route;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Walk extends BaseEntity{
    // 산책 기록 entity라는 것을 명심하기
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walkId;

    @Column(nullable = false)
    private Long userId;

    private String title;

    @ElementCollection(fetch = FetchType.LAZY)
    private Route route;

    private int walkTime;

    private int walkDistance;   // 산책 거리

    @Column(nullable = false)
    @ColumnDefault("0")
    private int likeCount;

    private List<Long> completedAchievement;

    private Long participateEvent;

    private List<PostOnWalk> postList;
}
