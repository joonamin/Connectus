package social.connectus.domain.service;

import java.util.List;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.request.CreateFeedRequestDto;
import social.connectus.application.rest.request.CreatePostRequest;
import social.connectus.common.annotation.UseCase;
import social.connectus.common.exception.GlobalException;
import social.connectus.domain.ports.inbound.CreatePostUseCase;
import social.connectus.domain.ports.outbound.CreatePostPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreatePostService implements CreatePostUseCase {
	private final CreatePostPort createPostPort;
	@Override
	public String createPost(CreateFeedRequestDto requestDto) throws GlobalException {
		try {

		} catch (Exception e) {
			throw new GlobalException("createPost : " + e.getMessage());
		}
		return createPostPort.createPost(requestDto);
	}
}
