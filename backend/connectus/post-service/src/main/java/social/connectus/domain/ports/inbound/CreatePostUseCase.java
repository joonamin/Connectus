package social.connectus.domain.ports.inbound;

import java.util.List;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.request.CreateFeedRequestDto;
import social.connectus.application.rest.request.CreatePostRequest;
import social.connectus.common.exception.GlobalException;

public interface CreatePostUseCase {
	String createPost(CreateFeedRequestDto requestDto) throws GlobalException;
}
