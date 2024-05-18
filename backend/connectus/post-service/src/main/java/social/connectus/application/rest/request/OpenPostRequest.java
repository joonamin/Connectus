package social.connectus.application.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OpenPostRequest {
	private Long postId;
	private Long userId;
}
