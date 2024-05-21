package social.connectus.infrastructure.databases;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import social.connectus.common.type.Type;
import social.connectus.domain.model.Likes;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long>, LikeRepositoryCustom {
	Optional<Likes> findByUserIdAndDomainIdAndType(Long userId, Long domainId, Type type);
}
