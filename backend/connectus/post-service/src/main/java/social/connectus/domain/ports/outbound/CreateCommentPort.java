package social.connectus.domain.ports.outbound;

import social.connectus.application.rest.request.CreateCommentRequestDto;
import social.connectus.common.exception.NotFoundException;

public interface CreateCommentPort {
	String createComment(Long postId, CreateCommentRequestDto dto) throws NotFoundException;
}
