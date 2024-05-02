package social.connectus.infrastructure.databases.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import social.connectus.domain.model.Candidate;
import social.connectus.domain.ports.outbound.GetCandidateUserIdGatherPort;

import java.util.Optional;

@Component
public class GetCandidateUserIdGatherAdapter implements GetCandidateUserIdGatherPort {
    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Optional<Candidate> getCandidateByUserIdAndGatherId(long userId, long gatherId) {
        return candidateRepository.findByUserIdAndGatherId(userId, gatherId);
    }
}
