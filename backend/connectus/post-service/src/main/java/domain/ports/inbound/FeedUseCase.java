package domain.ports.inbound;

import org.springframework.data.domain.Slice;

import application.rest.request.CoordinateRequestDto;
import application.rest.response.FeedResponse;
import common.exception.GlobalException;

public interface FeedUseCase {
	FeedResponse feedDetail(Long walkId) throws GlobalException;
}
