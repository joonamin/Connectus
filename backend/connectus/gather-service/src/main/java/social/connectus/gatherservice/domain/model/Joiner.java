package social.connectus.gatherservice.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Joiner extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="joiner_id")
    private long joinerId;

    @Column(nullable = false)
    private long userId;

//    @JoinColumn(name="gather_id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Gather gather;

}
