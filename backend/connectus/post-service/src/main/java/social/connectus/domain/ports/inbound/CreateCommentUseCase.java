package social.connectus.domain.ports.inbound;

import social.connectus.application.rest.request.CreateCommentRequestDto;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.exception.NotFoundException;

public interface CreateCommentUseCase {
	String createComment(Long postId, CreateCommentRequestDto dto) throws GlobalException, NotFoundException;
}
