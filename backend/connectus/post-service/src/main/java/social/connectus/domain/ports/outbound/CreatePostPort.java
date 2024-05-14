package social.connectus.domain.ports.outbound;

import java.util.List;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.request.CreateFeedRequestDto;
import social.connectus.application.rest.request.CreatePostRequest;

public interface CreatePostPort {
	String createPost(CreateFeedRequestDto requestDto);

}
