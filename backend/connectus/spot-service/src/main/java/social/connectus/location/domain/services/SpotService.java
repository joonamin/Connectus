package social.connectus.location.domain.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import social.connectus.location.domain.ports.inbound.SpotUseCase;

@RequiredArgsConstructor
@Transactional
@Component
public class SpotService implements SpotUseCase {
    @Override
    public void test() {

    }
}
