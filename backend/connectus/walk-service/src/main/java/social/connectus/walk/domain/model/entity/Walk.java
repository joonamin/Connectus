package social.connectus.walk.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import social.connectus.walk.domain.model.VO.Position;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Walk extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walkId;

    @Column(nullable = false)
    private Long userId;

    private String title;

    @Embedded
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Position> route;

    private int walkTime;

    private int walkDistance;   // 산책 거리

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Long> likeUsers;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Long> completedAchievement;

    private Long participateEvent;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Long> trackingUsers;

    @ColumnDefault("false")
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isPublic;

}
