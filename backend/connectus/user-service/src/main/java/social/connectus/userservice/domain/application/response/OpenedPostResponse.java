package social.connectus.userservice.domain.application.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OpenedPostResponse {
	private List<Long> openedPostList;
}
