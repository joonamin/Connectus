package social.connectus.walk.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompletedAchievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long achievementId;

    @ManyToOne
    @JoinColumn(name = "walk_id")
    private Walk walk;

    public CompletedAchievement(long achievementId){
        this.achievementId = achievementId;
    }
}
