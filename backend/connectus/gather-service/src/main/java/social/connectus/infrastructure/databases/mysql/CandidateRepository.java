package social.connectus.infrastructure.databases.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import social.connectus.domain.model.Candidate;
import social.connectus.domain.model.Gather;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByUserIdAndGatherId(long userId, long gatherId);
}
