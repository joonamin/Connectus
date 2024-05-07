package social.connectus.location.common.type;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ping {
    private long id;
    private double longitude;
    private double latitude;
    private PingType type;
    private long domainId;
    private String contents;
}
