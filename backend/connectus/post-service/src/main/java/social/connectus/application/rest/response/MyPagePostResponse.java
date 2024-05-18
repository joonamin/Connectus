package social.connectus.application.rest.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.domain.model.RDBMS.Post;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MyPagePostResponse {
	private Long postId;
	private String content;
	private LocalDateTime updatedAt;
	private int commentCount;
	private int likeCount;
	private String imageUrl;

	public static MyPagePostResponse from(Post post, int likeCount) {
		return MyPagePostResponse.builder()
			.postId(post.getId())
			.content(post.getContent())
			.commentCount(post.getCommentList().size())
			.likeCount(likeCount)
			.updatedAt(post.getUpdatedAt())
			.imageUrl(post.getImageUrl())
			.build();
	}
}
