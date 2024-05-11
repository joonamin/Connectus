package social.connectus.domain.ports.outbound;

import java.util.List;

import social.connectus.application.rest.response.FeedResponse;

public interface FeedPort {
	List<FeedResponse> feedMain(List<Long> walkIdList);
	FeedResponse feedDetail(Long walkId);
}
