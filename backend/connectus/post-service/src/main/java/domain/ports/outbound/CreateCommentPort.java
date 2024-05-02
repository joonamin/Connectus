package domain.ports.outbound;

import application.rest.request.CreateCommentRequestDto;
import application.rest.response.CreateCommentResponse;

public interface CreateCommentPort {
	String createComment(Long postId, CreateCommentRequestDto dto);
}
