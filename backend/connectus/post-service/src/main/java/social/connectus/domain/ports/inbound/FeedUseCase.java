package social.connectus.domain.ports.inbound;

import java.util.List;

import org.springframework.data.domain.Slice;

import social.connectus.application.rest.response.FeedResponse;
import social.connectus.common.exception.GlobalException;

public interface FeedUseCase {
	List<FeedResponse> feedMain(List<Long> walkId);
	FeedResponse feedDetail(Long walkId) throws GlobalException;
}
