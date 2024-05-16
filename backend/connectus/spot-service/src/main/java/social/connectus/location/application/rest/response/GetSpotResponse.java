package social.connectus.location.application.rest.response;

import lombok.*;
import social.connectus.location.application.rest.request.SpotDto;
import social.connectus.location.common.type.PingType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetSpotResponse {
    List<SpotDto> spotList;
}
