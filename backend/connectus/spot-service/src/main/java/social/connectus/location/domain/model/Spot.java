package social.connectus.location.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import social.connectus.location.common.type.PingType;

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
    private PingType type;

    // domain 저장, spot 저장의 `순환참조` 문제를 해결하기 위해서, 임시적으로 nullable = true로 설정합니다.
    @Column(name = "domain_id", nullable = true)
    private Long domainId;
}
