package social.connectus.gatherservice.application.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.gatherservice.common.type.Type;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSpotRequest {
    private List<SpotDto> spotList;
}
