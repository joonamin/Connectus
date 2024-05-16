package social.connectus.location.domain.command;

import lombok.*;
import social.connectus.location.application.rest.request.CreateSpotRequest;
import social.connectus.location.application.rest.request.SpotDto;
import social.connectus.location.domain.model.Spot;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSpotCommand {
    List<SpotDto> spotList;

    public static CreateSpotCommand from(CreateSpotRequest request){
        return CreateSpotCommand.builder()
                .spotList(request.getSpotList())
                .build();
    }
}
