package social.connectus.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="gather")
public class Gather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private long hostId;
    @Column
    private String content;
    @Column(nullable = false)
    private int maxJoiner;
    @Column(nullable = false)
    private String endTime;
}
