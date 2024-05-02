package social.connectus.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long candidate_id;

    @Column(nullable = false)
    private long user_id;

    @JoinColumn(name="gather_id")
    @ManyToOne
    private Gather gather;
}
