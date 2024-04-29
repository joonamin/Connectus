package social.connectus.infrastructure.databases.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import social.connectus.domain.model.Gather;

public interface GatherRepository extends JpaRepository<Gather, Long> {

}
