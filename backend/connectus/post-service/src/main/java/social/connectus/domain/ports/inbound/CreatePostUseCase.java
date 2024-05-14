package social.connectus.domain.ports.inbound;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.request.CreateFeedRequestDto;
import social.connectus.application.rest.request.CreatePostRequest;
import social.connectus.common.exception.GlobalException;

public interface CreatePostUseCase {
	List<Long> createPost(CreateFeedRequestDto requestDto) throws GlobalException, IOException;
}
