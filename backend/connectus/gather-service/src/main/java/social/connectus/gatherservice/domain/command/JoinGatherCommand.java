package social.connectus.gatherservice.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinGatherCommand {
    private long gatherId;
    private long userId;
}
