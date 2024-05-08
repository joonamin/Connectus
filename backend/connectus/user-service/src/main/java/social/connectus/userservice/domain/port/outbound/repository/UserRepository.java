package social.connectus.userservice.domain.port.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import social.connectus.userservice.domain.model.entity.User;
import social.connectus.userservice.domain.port.outbound.UserPort;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

}
