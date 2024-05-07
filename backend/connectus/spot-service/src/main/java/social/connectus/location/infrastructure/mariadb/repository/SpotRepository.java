package social.connectus.location.infrastructure.mariadb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import social.connectus.location.domain.model.Spot;

public interface SpotRepository  extends JpaRepository<Spot, Long> {
}