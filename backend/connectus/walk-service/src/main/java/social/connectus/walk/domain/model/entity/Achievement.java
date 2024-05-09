package social.connectus.walk.domain.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Achievement {
    @Id
    private long id;

    @ManyToOne
    private Walk walk;

    public Achievement(long id){
        this.id = id;
    }

}
