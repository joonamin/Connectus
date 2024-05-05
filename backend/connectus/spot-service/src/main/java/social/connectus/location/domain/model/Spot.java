package social.connectus.location.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Spot extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="spot_id")
    private Long id;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PinType type;

    @Column(name = "domain_id", nullable = false)
    private Long domainId;
}
