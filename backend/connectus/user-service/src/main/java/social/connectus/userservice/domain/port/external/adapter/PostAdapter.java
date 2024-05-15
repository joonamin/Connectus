package social.connectus.userservice.domain.port.external.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import social.connectus.userservice.domain.port.client.PostClient;
import social.connectus.userservice.domain.port.client.response.PostRecord;
import social.connectus.userservice.domain.port.external.port.PostPort;

@Component
@RequiredArgsConstructor
public class PostAdapter implements PostPort {

	private final PostClient postClient;

	@Override
	public List<PostRecord> getMyPreferencePost(Long userId) {
		return postClient.getUsersPreferencePost(userId);
	}
}
