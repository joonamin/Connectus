package social.connectus.location.infrastructure.mariadb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import social.connectus.location.domain.model.Spot;

@Repository
public interface SpotRepository  extends JpaRepository<Spot, Long> {
}