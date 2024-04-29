package social.connectus.infrastructure.databases.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import social.connectus.domain.model.Gather;
import social.connectus.domain.ports.outbound.CreateGatherPort;

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
