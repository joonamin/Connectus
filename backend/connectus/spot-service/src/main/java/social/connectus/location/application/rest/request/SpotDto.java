package social.connectus.location.application.rest.request;

import lombok.*;
import social.connectus.location.common.type.PingType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpotDto {
    private Double longitude;
    private Double latitude;
    private PingType type;
    private Long domainId;
}
