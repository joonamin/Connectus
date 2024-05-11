package social.connectus.infrastructure.databases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import social.connectus.domain.model.Likes;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long>, LikeRepositoryCustom {
	boolean existsByDomainId(Long domainId);
}
