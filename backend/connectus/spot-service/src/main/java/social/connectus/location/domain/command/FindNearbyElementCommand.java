package social.connectus.location.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FindNearbyElementCommand {
    private final double longitude;
    private final double latitude;
}
