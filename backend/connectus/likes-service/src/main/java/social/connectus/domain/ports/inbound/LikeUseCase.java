package social.connectus.domain.ports.inbound;

import java.util.List;

import social.connectus.application.rest.response.LikesResponse;
import social.connectus.common.annotation.UseCase;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.type.Type;

public interface LikeUseCase {
	String insertLike(Long domainId, Long userId, Type type) throws GlobalException;

	int getLikeCount(Long domainId, Type type) throws GlobalException;

	boolean isLike(Long domainId, Long userId, Type type);
	List<Long> getLikesList(Long userId);
}
