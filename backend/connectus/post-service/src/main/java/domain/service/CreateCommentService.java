package domain.service;

import application.rest.request.CreateCommentRequestDto;
import common.annotation.UseCase;
import common.exception.GlobalException;
import common.exception.ParameterNotFoundException;
import domain.ports.inbound.CreateCommentUseCase;
import domain.ports.outbound.CreateCommentPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateCommentService implements CreateCommentUseCase {
	private final CreateCommentPort createCommentPort;
	@Override
	public String createComment(Long postId, CreateCommentRequestDto dto) throws GlobalException {
		try{
			if (dto.getAuthorId() == null) {
				new ParameterNotFoundException("authorId");
			}

		} catch (Exception e) {
			throw new GlobalException("createPost : " + e.getMessage());
		}

		return createCommentPort.createComment(postId, dto);
	}
}
