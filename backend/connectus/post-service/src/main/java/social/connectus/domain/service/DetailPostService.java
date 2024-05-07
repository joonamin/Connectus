package social.connectus.domain.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.response.DetailPostResponse;
import social.connectus.common.annotation.UseCase;
import social.connectus.common.exception.BusinessException;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.exception.ParameterNotFoundException;
import social.connectus.domain.ports.inbound.DetailPostUseCase;
import social.connectus.domain.ports.outbound.DetailPostPort;

@UseCase
@RequiredArgsConstructor
public class DetailPostService implements DetailPostUseCase {
	private final DetailPostPort detailPostPort;
	@Override
	public DetailPostResponse detailByUserExperience(Long postId, Long userId) throws GlobalException,
		BusinessException {
		try {
			if(postId == null) {
				throw new ParameterNotFoundException("authorId");
			}
			if(userId == null) {
				throw new ParameterNotFoundException("userId");
			}
			List<Long> openedPostListByUserId = detailPostPort.openedPostByUserId(userId).getOpenedPostList();
			if (!openedPostListByUserId.contains(postId)) {
				throw new BusinessException("You haven't seen this post.");
			}
		} catch (Exception e) {
			throw new GlobalException("DetailPostService : " + e.getMessage());
		}

		return detailPostPort.detailPost(postId);
	}

	@Override
	public DetailPostResponse detailByLocation(Long postId, CoordinateRequestDto userLocation) throws GlobalException, BusinessException {
		CoordinateRequestDto postLocation = detailPostPort.postLocationByPostId(postId);
		try {
			if(postId == null) {
				throw new ParameterNotFoundException("postId");
			}
			// TODO : R 크기 정해지면 수정
			if(getDistance(userLocation, postLocation) < 9) {
				throw new BusinessException("post is too far");
			}
		} catch (Exception e) {
			throw new GlobalException("DetailPostService : " + e.getMessage());
		}
		DetailPostResponse response = detailPostPort.detailPost(postId);
		return response;
	}
	private double getDistance(CoordinateRequestDto userLocation, CoordinateRequestDto postLocation) {
		double dx, dy;
		dx = Math.pow(userLocation.getLatitude() - postLocation.getLatitude(), 2.0);
		dy = Math.pow(userLocation.getLongitude() - postLocation.getLongitude(), 2.0);

		return Math.sqrt(dy + dx);
	}
}
