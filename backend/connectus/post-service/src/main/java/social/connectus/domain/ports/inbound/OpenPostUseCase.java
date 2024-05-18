package social.connectus.domain.ports.inbound;

import social.connectus.application.rest.request.OpenPostRequest;
import social.connectus.application.rest.response.PointResponse;
import social.connectus.common.exception.GlobalException;

public interface OpenPostUseCase {
	PointResponse openPost(OpenPostRequest request) throws GlobalException;
}
