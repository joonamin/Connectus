package social.connectus.walk.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;
import social.connectus.walk.domain.model.VO.Position;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "walk_id")
    private Walk walk;
}
