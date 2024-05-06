package social.connectus.walk.domain.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    @Column(name="walk_id")
    private Long walkId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String title;

    @ElementCollection(fetch = FetchType.LAZY)
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
