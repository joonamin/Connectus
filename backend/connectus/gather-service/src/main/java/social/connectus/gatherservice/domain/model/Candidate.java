package social.connectus.gatherservice.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidate extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="candidate_id")
    private long candidateId;

    @Column(nullable = false)
    private long userId;

//    @JoinColumn(name="gather_id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Gather gather;
//
//    public Candidate(long userId, Gather gather) {
//        this.userId = userId;
//        this.gather = gather;
//    }
}
