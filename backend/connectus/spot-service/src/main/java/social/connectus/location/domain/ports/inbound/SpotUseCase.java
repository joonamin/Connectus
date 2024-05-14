package social.connectus.location.domain.ports.inbound;

import org.springframework.stereotype.Component;
import social.connectus.location.application.rest.response.FindNearbyElementResponse;
import social.connectus.location.common.customannotations.UseCase;
import social.connectus.location.domain.command.FindNearbyElementCommand;

@UseCase
@Component
public interface SpotUseCase {
    FindNearbyElementResponse findNearbyElement(FindNearbyElementCommand command);
}
