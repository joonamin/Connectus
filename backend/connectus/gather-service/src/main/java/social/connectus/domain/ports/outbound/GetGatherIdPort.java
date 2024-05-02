package social.connectus.domain.ports.outbound;

import social.connectus.domain.model.Gather;

public interface GetGatherPort {
    Gather getGather(long gather_id);
}
