package infrastructure.databases.mariadb.adapter;

import org.springframework.stereotype.Component;

import application.rest.request.CreateCommentRequestDto;
import application.rest.response.CreateCommentResponse;
import domain.model.Comment;
import domain.ports.outbound.CreateCommentPort;
import infrastructure.databases.mariadb.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateCommentAdapter implements CreateCommentPort {
	private final CommentRepository commentRepository;
	@Override
	public String createComment(Long postId, CreateCommentRequestDto dto) {
		Comment comment = Comment.from(dto, postId);
		commentRepository.save(comment);
		return "comment save";
	}
}
