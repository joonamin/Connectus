package social.connectus.domain.ports.inbound;

import java.util.List;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.response.DetailPostResponse;
import social.connectus.application.rest.response.GetPostSpotResponse;
import social.connectus.common.exception.BusinessException;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.exception.NotFoundException;

public interface DetailPostUseCase {
	DetailPostResponse detailByUserExperience(Long postId, Long userId) throws GlobalException, BusinessException, NotFoundException;
//	DetailPostResponse detailByLocation(Long postId, CoordinateRequestDto coordinate) throws GlobalException, BusinessException;
	DetailPostResponse detailByPostId(Long postId,Long userId, Double distance) throws
			GlobalException,
			BusinessException, NotFoundException;

	String healthCheck();

    GetPostSpotResponse getPostSpotByPostId(Long postId) throws NotFoundException;
}
