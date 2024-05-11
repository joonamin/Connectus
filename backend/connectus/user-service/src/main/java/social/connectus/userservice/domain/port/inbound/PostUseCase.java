package social.connectus.userservice.domain.port.inbound;

import social.connectus.userservice.domain.application.request.MyPreferencePostRequest;
import social.connectus.userservice.domain.application.response.MyPreferencePostResponse;
import social.connectus.userservice.domain.port.inbound.command.MyPreferencePostCommand;

public interface PostUseCase {
	// 내가 좋아요 누른 방명록 정보 가져오기
	MyPreferencePostResponse getMyPreferencePost(MyPreferencePostCommand request);
}
