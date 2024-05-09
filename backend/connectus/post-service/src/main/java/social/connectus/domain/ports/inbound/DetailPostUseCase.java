package social.connectus.domain.ports.inbound;

import java.util.List;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.response.DetailPostResponse;
import social.connectus.common.exception.BusinessException;
import social.connectus.common.exception.GlobalException;

public interface DetailPostUseCase {
	DetailPostResponse detailByUserExperience(Long postId, Long userId) throws GlobalException, BusinessException;
	DetailPostResponse detailByLocation(Long postId, CoordinateRequestDto coordinate) throws GlobalException, BusinessException;
	DetailPostResponse detailByPostId(Long postId,Long userId, Double distance) throws
		GlobalException,
		BusinessException;

	String healthCheck();
}
