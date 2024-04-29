package social.connectus.domain.ports.inbound;

import social.connectus.domain.model.Gather;

public interface CreateGatherUseCase {
    void createGather(Gather gather);
}
