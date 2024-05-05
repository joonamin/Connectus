package social.connectus.location.domain.ports.inbound;

import org.springframework.stereotype.Component;
import social.connectus.location.common.customannotations.UseCase;

@UseCase
@Component
public interface SpotUseCase {
    void test();
}
