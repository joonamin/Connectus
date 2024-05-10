package social.connectus.domain.ports.inbound;

import java.util.List;

import social.connectus.application.rest.response.MainPostResponse;
import social.connectus.common.exception.GlobalException;

public interface MainPostUseCase {
	List<MainPostResponse> mainPost(List<Long> postId) throws GlobalException;
}
