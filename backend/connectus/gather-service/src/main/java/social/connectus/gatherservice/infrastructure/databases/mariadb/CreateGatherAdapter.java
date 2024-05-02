package social.connectus.gatherservice.infrastructure.databases.mariadb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import social.connectus.gatherservice.domain.model.Gather;
import social.connectus.gatherservice.domain.ports.outbound.CreateGatherPort;

@Component
public class CreateGatherAdapter implements CreateGatherPort {
    @Autowired
    private GatherRepository gatherRepository;
    @Override
    public void createGather(Gather gather) {
        gatherRepository.save(gather);
        // send
        // poll(consume)
    }
}
