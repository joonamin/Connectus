package social.connectus.application.rest.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import social.connectus.domain.model.RDBMS.Post;
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MainPostResponse {
	private Long authorId;
	private Long walkId;
	private LocalDateTime updatedAt;
	private int likeCount;
	private int commentCount;
	private String partContent;

	public static MainPostResponse from(Post post, int likeCount) {
		String content = post.getContent();
		if(content.length() > 15) {
			content = content.substring(0,15);
		}
		return MainPostResponse.builder()
			.authorId(post.getAuthorId())
			.walkId(post.getWalkId())
			.updatedAt(post.getUpdatedAt())
			.likeCount(likeCount)
			.commentCount(post.getCommentList().size())
			.partContent(content)
			.build();
	}
}
