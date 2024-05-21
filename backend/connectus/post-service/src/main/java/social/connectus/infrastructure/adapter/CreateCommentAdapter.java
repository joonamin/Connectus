package social.connectus.infrastructure.adapter;

import org.springframework.stereotype.Component;

import social.connectus.application.rest.request.CreateCommentRequestDto;
import social.connectus.common.exception.NotFoundException;
import social.connectus.domain.model.RDBMS.Comment;
import social.connectus.domain.model.RDBMS.Post;
import social.connectus.domain.ports.outbound.CreateCommentPort;
import social.connectus.infrastructure.databases.mariadb.repository.CommentRepository;
import social.connectus.infrastructure.databases.mariadb.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateCommentAdapter implements CreateCommentPort {
	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	@Override
	public String createComment(Long postId, CreateCommentRequestDto dto) throws NotFoundException {
		Post post = postRepository.findById(postId).orElseThrow(()-> new NotFoundException("Not Found Post"));
		Comment comment = Comment.from(dto, post);
		commentRepository.save(comment);
		return "comment save";
	}
}
