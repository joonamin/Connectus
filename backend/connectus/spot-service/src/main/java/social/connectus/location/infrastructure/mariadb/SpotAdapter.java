package social.connectus.location.infrastructure.mariadb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import social.connectus.location.domain.command.FindNearbyElementCommand;
import social.connectus.location.domain.model.Spot;
import social.connectus.location.domain.ports.outbound.SpotPort;
import social.connectus.location.infrastructure.mariadb.repository.SpotRepository;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class SpotAdapter implements SpotPort {

//    private final SpotRepository spotRepository;

}
