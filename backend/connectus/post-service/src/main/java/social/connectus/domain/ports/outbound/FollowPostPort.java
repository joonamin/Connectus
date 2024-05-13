package social.connectus.domain.ports.outbound;

import social.connectus.application.rest.response.FollowPostResponse;

public interface FollowPostPort {
	FollowPostResponse followPost(Long postId);
}
