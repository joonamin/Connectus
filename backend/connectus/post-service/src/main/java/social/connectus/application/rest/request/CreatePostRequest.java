package social.connectus.application.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatePostRequest {
	private String content;
	private String imageUrl;
	private Long authorId;
	private Long walkId;
}
