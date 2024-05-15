package social.connectus.gatherservice.application.rest.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetGatherResponse {
    private long gatherId;
    private boolean isClosed;
    private String content;
    private long hostId;
    private long spotId;
    private int maxJoiner;
    private Set<Long> candidateList;
    private Set<Long> joinerList;
    private String endTime;
    private LocalDateTime updatedAt;
}
