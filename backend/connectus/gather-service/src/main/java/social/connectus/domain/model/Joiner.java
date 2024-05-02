package social.connectus.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "joiner")
public class Joiner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long joiner_id;

    @Column(nullable = false)
    private long user_id;

    @JoinColumn(name="gather_id")
    @ManyToOne
    private Gather gather;
}
