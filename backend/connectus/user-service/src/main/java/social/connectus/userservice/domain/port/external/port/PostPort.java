package social.connectus.userservice.domain.port.external.port;

import java.util.List;

import social.connectus.userservice.domain.port.client.response.PostRecord;

public interface PostPort {
	// userId에 해당하는 유저가 좋아하는 방명록 리스트를 출력한다.
	List<PostRecord> getMyPreferencePost(Long userId);
}
