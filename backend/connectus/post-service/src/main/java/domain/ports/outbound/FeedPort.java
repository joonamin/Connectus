package domain.ports.outbound;

import org.springframework.data.domain.Slice;

import application.rest.request.CoordinateRequestDto;
import application.rest.response.FeedResponse;
import domain.model.Feed;

public interface FeedPort {
	FeedResponse feedDetail(Long walkId);
}
