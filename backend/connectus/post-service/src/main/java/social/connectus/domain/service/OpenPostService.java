package social.connectus.domain.service;

import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.request.OpenPostRequest;
import social.connectus.application.rest.response.PointResponse;
import social.connectus.common.annotation.UseCase;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.exception.ParameterNotFoundException;
import social.connectus.domain.ports.inbound.OpenPostUseCase;
import social.connectus.domain.ports.outbound.OpenPostPort;

@UseCase
@RequiredArgsConstructor
public class OpenPostService implements OpenPostUseCase {
	private final OpenPostPort openPostPort;
	@Override
	public PointResponse openPost(OpenPostRequest request) throws GlobalException {
		try {
			if(request.getPostId() == null) {
				throw new ParameterNotFoundException("postId");
			}
			if(request.getUserId() == null) {
				throw new ParameterNotFoundException("userId");
			}
		} catch (Exception e) {
			throw new GlobalException("OpenPostService : " + e.getMessage());
		}
		return openPostPort.insertOpenPost(request);
	}
}
