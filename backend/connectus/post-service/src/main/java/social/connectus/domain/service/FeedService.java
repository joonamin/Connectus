package social.connectus.domain.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.response.FeedResponse;
import social.connectus.common.annotation.UseCase;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.exception.ParameterNotFoundException;
import social.connectus.domain.ports.inbound.FeedUseCase;
import social.connectus.domain.ports.outbound.FeedPort;

@UseCase
@RequiredArgsConstructor
public class FeedService implements FeedUseCase {
	private final FeedPort feedPort;
	@Override
	public List<FeedResponse> feedMain(List<Long> walkId) {
		return feedPort.feedMain(walkId);
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
