package social.connectus.infrastructure.databases.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import social.connectus.domain.model.Gather;
import social.connectus.domain.ports.outbound.GetGatherIdPort;

import java.util.Optional;

@Component
public class GetGatherIdAdapter implements GetGatherIdPort {

    @Autowired
    private GatherRepository gatherRepository;

    @Override
    public Optional<Gather> getGatherById(long gatherId) {
        return gatherRepository.findById(gatherId);
    }
}
