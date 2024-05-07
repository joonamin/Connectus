package social.connectus.domain.service;

import social.connectus.application.rest.request.CreateCommentRequestDto;
import social.connectus.common.annotation.UseCase;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.exception.NotFoundException;
import social.connectus.common.exception.ParameterNotFoundException;
import social.connectus.domain.ports.inbound.CreateCommentUseCase;
import social.connectus.domain.ports.outbound.CreateCommentPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateCommentService implements CreateCommentUseCase {
	private final CreateCommentPort createCommentPort;
	@Override
	public String createComment(Long postId, CreateCommentRequestDto dto) throws GlobalException, NotFoundException {
		try{
			if (dto.getAuthorId() == null) {
				throw new ParameterNotFoundException("authorId");
			}

		} catch (Exception e) {
			throw new GlobalException("createPost : " + e.getMessage());
		}

		return createCommentPort.createComment(postId, dto);
	}
}
