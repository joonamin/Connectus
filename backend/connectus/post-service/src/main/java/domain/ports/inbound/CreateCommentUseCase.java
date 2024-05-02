package domain.ports.inbound;

import application.rest.request.CreateCommentRequestDto;
import application.rest.response.CreateCommentResponse;
import common.exception.GlobalException;

public interface CreateCommentUseCase {
	String createComment(Long postId, CreateCommentRequestDto dto) throws GlobalException;
}
