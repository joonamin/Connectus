package social.connectus.application.rest.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import social.connectus.domain.model.RDBMS.Comment;
import social.connectus.domain.model.RDBMS.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailPostResponse {
	private Long postId;
	private Long walkId;
	private String imageUrl;
	private String content;
	private Long authorId;
	private int likeCount;
	private List<Comment> commentList;
	private LocalDateTime updatedAt;
	private boolean isLike;
	private boolean inRange;

	public static DetailPostResponse detailPostFrom(Post post) {
		return DetailPostResponse.builder()
			.postId(post.getId())
			.authorId(post.getAuthorId())
			.imageUrl(post.getImageUrl())
			.content(post.getContent())
			.commentList(post.getCommentList())
			.updatedAt(post.getUpdatedAt())
			.inRange(true)
			.build();
	}

	public static DetailPostResponse samplePostFrom(Post post) {
		return DetailPostResponse.builder()
			.postId(post.getId())
			.authorId(post.getAuthorId())
			.imageUrl(post.getImageUrl())
			.commentList(post.getCommentList())
			.updatedAt(post.getUpdatedAt())
			.inRange(false)
			.build();
	}

}
