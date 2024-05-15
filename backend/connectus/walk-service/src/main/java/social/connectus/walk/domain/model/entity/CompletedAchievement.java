package social.connectus.walk.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompletedAchievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long achievementId;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isSuccess;

    @ManyToOne
    @JoinColumn(name = "walk_id")
    private Walk walk;

//    @JsonCreator
//    public CompletedAchievement(long achievementId, boolean isSuccess){
//        this.achievementId = achievementId;
//        this.isSuccess = isSuccess;
//    }
}
