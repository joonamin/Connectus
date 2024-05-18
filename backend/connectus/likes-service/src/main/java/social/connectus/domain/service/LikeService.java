package social.connectus.domain.service;

import org.hibernate.procedure.ParameterMisuseException;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import social.connectus.common.annotation.UseCase;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.type.Type;
import social.connectus.domain.ports.inbound.LikeUseCase;
import social.connectus.domain.ports.outbound.LikePort;

@UseCase
@RequiredArgsConstructor
public class LikeService implements LikeUseCase {
	private final LikePort likePort;

	@Override
	public String insertLike(Long domainId, Long userId, Type type) throws GlobalException {
		try {
			if (domainId == null) {
				throw new NotFoundException("postId");
			}
			if (userId == null) {
				throw new NotFoundException("userId");
			}
			if (type == null) {
				throw new ParameterMisuseException("type");
			}
		} catch (Exception e) {
			throw new GlobalException("LikeService[insertLike] : " + e.getMessage());
		}

		return likePort.insertLike(domainId, userId, type);
	}

	@Override
	public int getLikeCount(Long domainId, Type type) throws GlobalException {
		try {
			if (domainId == null) {
				throw new NotFoundException("domainId");
			}
			if (type == null) {
				throw new NotFoundException("type");
			}
		} catch (Exception e) {
			throw new GlobalException("LikeService[getLikeCount] : " + e.getMessage());
		}
		return likePort.getLikeCount(domainId, type);
	}

	@Override
	public boolean isLike(Long postId, Long userId, Type type) {
		return likePort.isLike(postId, userId, type);
	}
}
