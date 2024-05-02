package social.connectus.gatherservice.domain.ports.inbound;

import social.connectus.gatherservice.domain.model.Gather;

public interface CreateGatherUseCase {
    void createGather(Gather gather);
}
