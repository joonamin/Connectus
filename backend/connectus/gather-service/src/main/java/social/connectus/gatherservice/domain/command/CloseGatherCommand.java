package social.connectus.gatherservice.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.gatherservice.application.rest.request.CloseGatherRequest;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CloseGatherCommand {
    private long gatherId;
    private long userId;
    public static CloseGatherCommand from(CloseGatherRequest request){
        return CloseGatherCommand.builder()
                .gatherId(request.getGatherId())
                .userId(request.getUserId())
                .build();
    }
}
