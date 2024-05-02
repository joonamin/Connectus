package social.connectus.gatherservice.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gather extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gather_id")
    private long id;

    @ColumnDefault("false")
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isClosed;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private long hostId;

    @Column(nullable = false)
    private int maxJoiner;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Long> candidateList;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Long> joinerList;

    @Column(nullable = false)
    private String endTime;
}
