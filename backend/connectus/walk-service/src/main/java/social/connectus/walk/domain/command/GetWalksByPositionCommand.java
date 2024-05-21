package social.connectus.walk.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.application.rest.request.GetWalksByPositionRequest;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetWalksByPositionCommand {
    private double latitude;
    private double longitude;
    private double kmRadius;
    private int pageNumber;
    private int pageSize;

    public static GetWalksByPositionCommand from(GetWalksByPositionRequest request){
        return GetWalksByPositionCommand.builder()
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .kmRadius(request.getKmRadius())
                .pageNumber(request.getPageNumber())
                .pageSize(request.getPageSize())
                .build();
    }
}
