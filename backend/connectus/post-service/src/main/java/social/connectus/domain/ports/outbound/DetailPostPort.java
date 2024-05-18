package social.connectus.domain.ports.outbound;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.request.GetPostSpotRequest;
import social.connectus.application.rest.request.SpotDto;
import social.connectus.application.rest.response.DetailPostResponse;
import social.connectus.application.rest.response.GetPostSpotResponse;
import social.connectus.application.rest.response.OpenedPostResponse;
import social.connectus.common.exception.BusinessException;
import social.connectus.common.exception.NotFoundException;

public interface DetailPostPort {
	DetailPostResponse samplePost(Long postId, Long userId) throws BusinessException, NotFoundException;
	DetailPostResponse detailPost(Long postId, Long userId) throws BusinessException, NotFoundException;
	void updateOpenedPost(Long userId, Long postId);
	OpenedPostResponse openedPostByUserId(Long userId);

	SpotDto postSpotBySpotId(Long spotId);

	String healthCheck();

	GetPostSpotResponse getPostSpotById(Long postId) throws NotFoundException;
}
