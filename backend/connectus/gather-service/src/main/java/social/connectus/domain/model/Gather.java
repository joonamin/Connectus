package social.connectus.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="gather")
public class Gather {
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

    @OneToMany(mappedBy = "gather")
    private List<Candidate> candidateList = new ArrayList<>();

    @OneToMany(mappedBy = "gather")
    private List<Joiner> joinerList = new ArrayList<>();

    @Column(nullable = false)
    private String endTime;
}
