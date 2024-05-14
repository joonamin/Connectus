package social.connectus.gatherservice.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.connectus.gatherservice.application.rest.request.CreateGatherRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateGatherCommand {
    private double latitude;
    private double longitude;
    private long hostId;
    private String content;
    private int maxJoiner;
    private String endTime;

    public static CreateGatherCommand from(CreateGatherRequest request){
        return CreateGatherCommand.builder()
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .hostId(request.getHostId())
                .content(request.getContent())
                .maxJoiner(request.getMaxJoiner())
                .endTime(request.getEndTime())
                .build();
    }
}
