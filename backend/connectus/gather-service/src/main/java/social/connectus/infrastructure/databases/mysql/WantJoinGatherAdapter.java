package social.connectus.infrastructure.databases.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import social.connectus.domain.model.Candidate;
import social.connectus.domain.model.Gather;
import social.connectus.domain.ports.outbound.CloseGatherPort;
import social.connectus.domain.ports.outbound.WantJoinGatherPort;

@Component
public class WantJoinGatherAdapter implements WantJoinGatherPort {
    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public void wantJoinGather(Candidate candidate) {
        candidateRepository.save(candidate);
    }
}
