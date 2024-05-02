package application.rest.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import domain.model.Comment;
import domain.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailPostResponse {
	private String imageUrl;
	private String content;
	private Long authorId;
	private List<Comment> commentList;

	public static DetailPostResponse from(Post post) {
		return DetailPostResponse.builder()
			.authorId(post.getAuthorId())
			.imageUrl(post.getImageUrl())
			.content(post.getContent())
			.commentList(post.getCommentList())
			.build();
	}
}
