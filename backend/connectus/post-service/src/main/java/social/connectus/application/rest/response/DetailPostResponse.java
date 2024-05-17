package social.connectus.application.rest.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import social.connectus.application.rest.request.SpotDto;
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
	private String authorName;
	private String authorAvatarImage;
	private int likeCount;
	private List<CommentResponse> commentList;
	private int commentCount;
	private LocalDateTime updatedAt;
	private boolean isLike;
	private boolean inRange;
	private double latitude;
	private double longitude;

	public static DetailPostResponse detailPostFrom(Post post, List<CommentResponse> commentList, UserInfoResponse authorInfo, SpotDto postSpot) {
		return DetailPostResponse.builder()
			.postId(post.getId())
			.authorId(post.getAuthorId())
			.authorName(authorInfo.getAuthorNickName())
			.authorAvatarImage(authorInfo.getAvatarImageUrl())
			.imageUrl(post.getImageUrl())
			.content(post.getContent())
			.commentList(commentList)
			.commentCount(commentList.size())
			.updatedAt(post.getUpdatedAt())
			.inRange(true)
			.latitude(postSpot.getLatitude())
			.longitude(postSpot.getLongitude())
			.build();
	}

	public static DetailPostResponse samplePostFrom(Post post, UserInfoResponse authorInfo) {
		return DetailPostResponse.builder()
			.postId(post.getId())
			.authorId(post.getAuthorId())
			.authorName(authorInfo.getAuthorNickName())
			.authorAvatarImage(authorInfo.getAvatarImageUrl())
			.imageUrl(post.getImageUrl())
			.commentCount(post.getCommentList().size())
			.updatedAt(post.getUpdatedAt())
			.inRange(false)
			.build();
	}

}
