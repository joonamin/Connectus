package social.connectus.domain.ports.outbound;

import social.connectus.domain.model.Candidate;
import social.connectus.domain.model.Gather;

public interface WantJoinGatherPort {
    void wantJoinGather(Candidate candidate);
}
