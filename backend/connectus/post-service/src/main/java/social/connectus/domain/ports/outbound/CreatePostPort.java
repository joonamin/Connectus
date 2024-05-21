package social.connectus.domain.ports.outbound;

import java.util.List;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.request.CreateFeedRequestDto;
import social.connectus.application.rest.request.CreatePostRequest;
import social.connectus.domain.service.command.InsertPostCommand;

public interface CreatePostPort {
	List<Long> createPost(CreateFeedRequestDto createFeedRequestDto);

}
