package social.connectus.domain.service;

import org.apache.commons.lang.NullArgumentException;

import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.response.FollowPostResponse;
import social.connectus.common.annotation.UseCase;
import social.connectus.common.exception.GlobalException;
import social.connectus.domain.ports.inbound.FollowPostUseCase;
import social.connectus.domain.ports.outbound.FollowPostPort;

@UseCase
@RequiredArgsConstructor
public class FollowPostService implements FollowPostUseCase {
	private final FollowPostPort followPostPort;

	@Override
	public FollowPostResponse followPost(Long postId) throws GlobalException {
		try {
			if (postId == null) {
				throw new NullArgumentException("postId");
			}
		} catch (Exception e) {
			throw new GlobalException("FollowPostService : " + e.getMessage());
		}
		return followPostPort.followPost(postId);
	}
}
