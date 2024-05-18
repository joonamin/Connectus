package social.connectus.userservice.application.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyLikePost {
	private Long postId;
	private String content;
	private LocalDateTime updatedAt;
	private int commentCount;
	private int likeCount;
	private String imageUrl;
}
