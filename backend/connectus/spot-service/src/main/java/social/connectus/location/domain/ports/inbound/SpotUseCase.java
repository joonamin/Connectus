package social.connectus.location.domain.ports.inbound;

import org.springframework.stereotype.Component;
import social.connectus.location.application.rest.request.SpotDto;
import social.connectus.location.application.rest.response.FindNearbyElementResponse;
import social.connectus.location.common.customannotations.UseCase;
import social.connectus.location.common.type.DomainType;
import social.connectus.location.domain.command.CreateSpotCommand;
import social.connectus.location.domain.command.FindNearbyElementCommand;
import social.connectus.location.domain.command.GetSpotCommand;

import java.util.List;

@UseCase
@Component
public interface SpotUseCase {
    FindNearbyElementResponse findNearbyElement(FindNearbyElementCommand command);
    List<Long> createSpot(CreateSpotCommand command);

    List<SpotDto> getSpot(GetSpotCommand command);

    List<Long> updateSpot(CreateSpotCommand from);
}
