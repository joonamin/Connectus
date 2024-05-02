package social.connectus.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "joiner")
public class Joiner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long joinerId;

    @Column(nullable = false)
    private long userId;

    @JoinColumn(name="gather_id")
    @ManyToOne
    private Gather gather;

}
