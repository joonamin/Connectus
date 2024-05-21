package social.connectus.userservice.application.response;

import lombok.*;
import social.connectus.userservice.domain.port.inbound.command.SpotIdListDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePositionResponse {
    private Long spotId;

    public static ChangePositionResponse from(SpotIdListDto dto){
        return ChangePositionResponse.builder()
                .spotId(dto.getSpotIdList().get(0))
                .build();
    }
}
