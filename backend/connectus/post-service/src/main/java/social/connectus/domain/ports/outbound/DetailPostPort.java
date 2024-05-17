package social.connectus.domain.ports.outbound;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.request.GetPostSpotRequest;
import social.connectus.application.rest.request.SpotDto;
import social.connectus.application.rest.response.DetailPostResponse;
import social.connectus.application.rest.response.OpenedPostResponse;
import social.connectus.common.exception.BusinessException;

public interface DetailPostPort {
	DetailPostResponse samplePost(Long postId) throws BusinessException;
	DetailPostResponse detailPost(Long postId) throws BusinessException;
	void updateOpenedPost(Long userId, Long postId);
	OpenedPostResponse openedPostByUserId(Long userId);

	SpotDto postSpotBySpotId(Long spotId);

	String healthCheck();
}
