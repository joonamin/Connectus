package social.connectus.userservice.application.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InsertPostRequest {
	Long userId;
	Long postId;
}
