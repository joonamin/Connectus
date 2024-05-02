package domain.service;

import java.util.List;

import org.springframework.stereotype.Component;

import application.rest.request.CoordinateRequestDto;
import application.rest.response.DetailPostResponse;
import common.annotation.UseCase;
import common.exception.BusinessException;
import common.exception.GlobalException;
import common.exception.ParameterNotFoundException;
import common.messagequeue.KafkaConsumer;
import domain.ports.inbound.DetailPostUseCase;
import domain.ports.outbound.DetailPostPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DetailPostService implements DetailPostUseCase {
	private final DetailPostPort detailPostPort;
	private final KafkaConsumer kafkaConsumer;
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
			List<Long> openedPostListByUserId = null; // 유저 서비스에 opened List 받아와야 되는데 여긴 UseCase 내부인데?
			// 일단 유저 서비스에서 openedPostList 뱉어주는 로직 필요하고
			if (!openedPostListByUserId.contains(postId)) {
				throw new BusinessException("You haven't seen this post.");
			}
		} catch (Exception e) {
			throw new GlobalException("DetailPostService : " + e.getMessage());
		}
		DetailPostResponse response = detailPostPort.detailPost(postId);
		return response;
	}

	@Override
	public DetailPostResponse detailByLocation(Long postId, CoordinateRequestDto coordinate) throws GlobalException, BusinessException {
		try {
			if(postId == null) {
				throw new ParameterNotFoundException("authorId");
			}
		} catch (Exception e) {
			throw new GlobalException("DetailPostService : " + e.getMessage());
		}
		DetailPostResponse response = detailPostPort.detailPost(postId);
		return response;
	}
}
