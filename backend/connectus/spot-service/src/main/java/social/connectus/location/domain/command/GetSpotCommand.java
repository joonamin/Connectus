package social.connectus.location.domain.command;

import lombok.*;
import social.connectus.location.application.rest.request.GetSpotRequest;
import social.connectus.location.application.rest.request.SpotDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetSpotCommand {
    List<Long> spotIdList;

    public static GetSpotCommand from(GetSpotRequest request){
        return GetSpotCommand.builder()
                .spotIdList(request.getSpotIdList())
                .build();
    }
}
