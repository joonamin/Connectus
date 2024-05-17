package social.connectus.gatherservice.application.rest.request;

import lombok.*;
import social.connectus.gatherservice.common.type.Type;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpotDto {
    private Double latitude;
    private Double longitude;
    private Type type;
    private Long domainId;
}
