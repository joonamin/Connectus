package social.connectus.domain.ports.outbound;

import social.connectus.domain.model.Candidate;

import java.util.Optional;

public interface GetCandidateUserIdGatherPort {
    Optional<Candidate> getCandidateByUserIdAndGatherId(long userId, long gatherId);
}
