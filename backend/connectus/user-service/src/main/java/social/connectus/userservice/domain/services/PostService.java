package social.connectus.userservice.domain.services;

import java.util.List;

import lombok.RequiredArgsConstructor;
import social.connectus.userservice.common.annotation.UseCase;
import social.connectus.userservice.domain.application.response.MyPreferencePostResponse;
import social.connectus.userservice.domain.port.client.response.PostRecord;
import social.connectus.userservice.domain.port.inbound.PostUseCase;
import social.connectus.userservice.domain.port.inbound.command.MyPreferencePostCommand;
import social.connectus.userservice.domain.port.external.port.PostPort;

@UseCase
@RequiredArgsConstructor
public class PostService implements PostUseCase {

	private final PostPort postPort;

	@Override
	public MyPreferencePostResponse getMyPreferencePost(MyPreferencePostCommand command) {
		List<PostRecord> myPreferencePost = postPort.getMyPreferencePost(command.getUserId());
		return MyPreferencePostResponse.builder()
			.preferenceRecords(myPreferencePost)
			.build();
	}
}
