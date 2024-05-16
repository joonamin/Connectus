package social.connectus.infrastructure.databases;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import social.connectus.common.type.Type;
import social.connectus.domain.model.Likes;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long>, LikeRepositoryCustom {
	Optional<Likes> findByDomainIdAndType(Long domainId, Type type);
}
