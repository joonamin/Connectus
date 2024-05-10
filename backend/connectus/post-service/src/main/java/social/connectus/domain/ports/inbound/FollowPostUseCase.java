package social.connectus.domain.ports.inbound;

import social.connectus.application.rest.response.FollowPostResponse;
import social.connectus.common.exception.GlobalException;

public interface FollowPostUseCase {
	FollowPostResponse followPost(Long postId) throws GlobalException;
}
