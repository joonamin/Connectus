package social.connectus.location.domain.ports.inbound;

import org.springframework.stereotype.Component;
import social.connectus.location.application.rest.request.CreateSpotRequest;
import social.connectus.location.application.rest.response.FindNearbyElementResponse;
import social.connectus.location.common.customannotations.UseCase;
import social.connectus.location.domain.command.CreateSpotCommand;
import social.connectus.location.domain.command.FindNearbyElementCommand;

import java.util.List;

@UseCase
@Component
public interface SpotUseCase {
    FindNearbyElementResponse findNearbyElement(FindNearbyElementCommand command);
    List<Long> createSpot(CreateSpotCommand command);
}
