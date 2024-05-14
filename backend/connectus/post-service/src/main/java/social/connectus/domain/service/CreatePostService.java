package social.connectus.domain.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.request.CreateFeedRequestDto;
import social.connectus.application.rest.request.CreatePostRequest;
import social.connectus.application.rest.request.PostRequestDto;
import social.connectus.common.annotation.UseCase;
import social.connectus.common.exception.GlobalException;
import social.connectus.domain.ports.inbound.CreatePostUseCase;
import social.connectus.domain.ports.outbound.CreatePostPort;
import lombok.RequiredArgsConstructor;
import social.connectus.domain.ports.outbound.ImagePort;
import social.connectus.domain.service.command.InsertPostCommand;

@UseCase
@RequiredArgsConstructor
public class CreatePostService implements CreatePostUseCase {
	private final CreatePostPort createPostPort;
	private final ImagePort imagePort;
	@Override
	public List<Long> createPost(CreateFeedRequestDto requestDto) throws GlobalException, IOException {
		try {

		} catch (Exception e) {
			throw new GlobalException("createPost : " + e.getMessage());
		}
		List<InsertPostCommand> request = new ArrayList<>();
		for(PostRequestDto postRequestDto : requestDto.getPostList()) {
			InsertPostCommand command = InsertPostCommand.from(postRequestDto);
			if(postRequestDto.getImage() != null) {
				command.setImageUrl(imagePort.uploadImage(postRequestDto.getImage()));
			}
			request.add(command);
		}
		return createPostPort.createPost(requestDto.getWalkId(), request);
	}
}
