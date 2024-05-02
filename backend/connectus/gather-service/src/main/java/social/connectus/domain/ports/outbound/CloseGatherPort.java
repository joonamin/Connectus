package social.connectus.domain.ports.outbound;

import social.connectus.domain.model.Gather;

public interface CloseGatherPort {
    void closeGather(Gather gather);
}
