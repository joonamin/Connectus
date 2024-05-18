package social.connectus.infrastructure.adapter;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.request.OpenPostRequest;
import social.connectus.application.rest.response.PointResponse;
import social.connectus.common.exception.BusinessException;
import social.connectus.domain.ports.outbound.OpenPostPort;
import social.connectus.infrastructure.feignClient.UserServiceClient;

@Component
@RequiredArgsConstructor
public class OpenPostAdapter implements OpenPostPort {
	private final UserServiceClient userServiceClient;
	@Override
	public PointResponse insertOpenPost(OpenPostRequest request) {
		return userServiceClient.insertOpenPost(request);
	}
}
