package social.connectus.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;

import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.request.GetWalksByPositionRequest;
import social.connectus.application.rest.request.SpotDto;
import social.connectus.application.rest.response.FeedResponse;
import social.connectus.common.annotation.UseCase;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.exception.ParameterNotFoundException;
import social.connectus.common.utils.SliceResponse;
import social.connectus.domain.ports.inbound.FeedUseCase;
import social.connectus.domain.ports.outbound.FeedPort;
import social.connectus.infrastructure.feignClient.UserServiceClient;
import social.connectus.infrastructure.feignClient.WalkServiceClient;

@UseCase
@RequiredArgsConstructor
public class FeedService implements FeedUseCase {
	private final FeedPort feedPort;
	private final WalkServiceClient walkServiceClient;
	@Override
	public SliceResponse<FeedResponse> feedMain(Double longitude, Double latitude, int pageNum, Long userId) {
		SpotDto userPosition = SpotDto.builder()
				.latitude(latitude)
				.longitude(longitude)
				.build();
		SliceResponse<Long> walkIdList =
			walkServiceClient.getFeedList(GetWalksByPositionRequest.from(userPosition,pageNum,5,userId,1.0));

		List<FeedResponse> feedList = feedPort.feedMain(walkIdList.getContents());

		return new SliceResponse<>(
			feedList,walkIdList.isHasNext(),pageNum);
	}

	@Override
	public FeedResponse feedDetail(Long walkId) throws GlobalException {
		try {
			if(walkId == null) {
				throw new ParameterNotFoundException("walkId");
			}
		} catch (Exception e) {
			throw new GlobalException("createPost : " + e.getMessage());
		}
		return feedPort.feedDetail(walkId);
	}
}
