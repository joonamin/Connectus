package social.connectus.gatherservice.infrastructure.databases.mariadb;


import org.springframework.data.jpa.repository.JpaRepository;
import social.connectus.gatherservice.domain.model.Gather;

public interface GatherRepository extends JpaRepository<Gather, Long> {

}
