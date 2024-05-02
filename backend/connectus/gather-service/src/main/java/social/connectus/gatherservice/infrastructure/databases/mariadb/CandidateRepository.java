package social.connectus.gatherservice.infrastructure.databases.mariadb;


import org.springframework.data.jpa.repository.JpaRepository;
import social.connectus.gatherservice.domain.model.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
//    Optional<Candidate> findByUserIdAndGatherId(long userId, long gatherId);
//    List<Candidate> findByGatherId(long gatherId);
}
