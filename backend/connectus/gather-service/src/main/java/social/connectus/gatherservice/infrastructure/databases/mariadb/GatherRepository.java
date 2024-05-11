package social.connectus.gatherservice.infrastructure.databases.mariadb;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import social.connectus.gatherservice.domain.model.Gather;

@Repository
public interface GatherRepository extends JpaRepository<Gather, Long> {

}
