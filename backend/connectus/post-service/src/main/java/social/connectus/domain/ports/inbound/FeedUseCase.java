package social.connectus.domain.ports.inbound;

import java.util.List;

import org.springframework.data.domain.Slice;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.response.FeedResponse;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.utils.SliceResponse;

public interface FeedUseCase {
	SliceResponse<FeedResponse> feedMain(Double longitude, Double latitude , int pageNum, Long userId);
	FeedResponse feedDetail(Long walkId) throws GlobalException;
}
