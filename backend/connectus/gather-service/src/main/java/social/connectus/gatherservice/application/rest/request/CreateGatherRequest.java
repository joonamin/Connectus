package social.connectus.gatherservice.application.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGatherRequest {
    private double latitude;
    private double longitude;
    private long hostId;
    private String content;
    private int maxJoiner;
    private String endTime;
}
