package social.connectus.gatherservice.application.rest.response;

import lombok.*;
import social.connectus.gatherservice.application.rest.request.SpotDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetSpotResponse {
    private List<SpotDto> spotList;
}
