package social.connectus.domain.ports.outbound;

import social.connectus.domain.model.Gather;

public interface CreateGatherPort {
    void createGather(Gather gather);
}