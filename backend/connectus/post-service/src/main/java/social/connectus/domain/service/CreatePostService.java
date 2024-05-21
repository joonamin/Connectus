package social.connectus.domain.service;

import java.io.IOException;
import java.util.List;

import social.connectus.application.rest.request.CreateFeedRequestDto;
import social.connectus.common.annotation.UseCase;
import social.connectus.common.exception.GlobalException;
import social.connectus.domain.ports.inbound.CreatePostUseCase;
import social.connectus.domain.ports.outbound.CreatePostPort;
import lombok.RequiredArgsConstructor;
import social.connectus.domain.ports.outbound.ImagePort;
import social.connectus.domain.service.command.InsertPostCommand;
import social.connectus.domain.service.command.PostPositionCommand;
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
		return createPostPort.createPost(requestDto);
	}
}
