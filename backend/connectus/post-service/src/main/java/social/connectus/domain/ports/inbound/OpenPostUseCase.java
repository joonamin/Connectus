package social.connectus.domain.ports.inbound;

import social.connectus.application.rest.request.OpenPostRequest;
import social.connectus.common.exception.GlobalException;

public interface OpenPostUseCase {
	String openPost(OpenPostRequest request) throws GlobalException;
}
