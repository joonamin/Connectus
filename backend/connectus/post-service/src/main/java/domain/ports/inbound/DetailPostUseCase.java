package domain.ports.inbound;

import application.rest.request.CoordinateRequestDto;
import application.rest.response.DetailPostResponse;
import common.exception.BusinessException;
import common.exception.GlobalException;

public interface DetailPostUseCase {
	DetailPostResponse detailByUserExperience(Long postId, Long userId) throws GlobalException, BusinessException;
	DetailPostResponse detailByLocation(Long postId, CoordinateRequestDto coordinate) throws GlobalException, BusinessException;
}
