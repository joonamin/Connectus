package social.connectus.location.domain.services;

import io.milvus.v2.service.vector.response.QueryResp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import social.connectus.location.application.rest.response.FindNearbyElementResponse;
import social.connectus.location.domain.command.FindNearbyElementCommand;
import social.connectus.location.domain.ports.inbound.SpotUseCase;

@RequiredArgsConstructor
@Transactional
@Component
public class SpotService implements SpotUseCase {


    @Override
    public FindNearbyElementResponse findNearbyElement(FindNearbyElementCommand command) {
        return null;
    }
}
