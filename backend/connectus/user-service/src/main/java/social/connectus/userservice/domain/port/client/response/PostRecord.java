package social.connectus.userservice.domain.port.client.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRecord {
	private String author;
	private String postId;
	private String imageUrl;
	private String content;
	private int likeCount;
	private int commentCount;
}
