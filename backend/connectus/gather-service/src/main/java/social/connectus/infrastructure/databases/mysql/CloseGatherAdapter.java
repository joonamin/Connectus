package social.connectus.infrastructure.databases.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import social.connectus.domain.model.Gather;
import social.connectus.domain.ports.outbound.CloseGatherPort;

@Component
public class CloseGatherAdapter implements CloseGatherPort {
    @Autowired
    private GatherRepository gatherRepository;

    @Override
    public void closeGather(Gather gather) {
        gather.setClosed(true);
        gatherRepository.save(gather);
    }
}
