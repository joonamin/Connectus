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
    private long userId;

    @JoinColumn(name="gather_id")
    @ManyToOne
    private Gather gather;

    public Candidate(long userId, Gather gather) {
        this.userId = userId;
        this.gather = gather;
    }
}
