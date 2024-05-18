package social.connectus.userservice.domain.model.VO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpotDto {
    private Long spotId;
    private Long domainId;
    private Double longitude;
    private Double latitude;
    private String type;
}
