package social.connectus.gatherservice.domain.command;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WantJoinGatherCommand {
    private long gatherId;
    private long userId;
}
