package social.connectus.walk.infrastructure.databases.mariadb.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import social.connectus.walk.domain.model.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query(value="SELECT * from route group by walk_id HAVING sqrt(POW(abs(latitude - ?1) * ?4,2) + pow(abs(longitude - ?2) * ?5,2)) < ?3", nativeQuery = true)
    Slice<Route> findSliceByPosition(double userLatitude, double userLongitude, double kmRadius, double KM_PER_LAT, double KM_PER_LON, Pageable pageable);

}
