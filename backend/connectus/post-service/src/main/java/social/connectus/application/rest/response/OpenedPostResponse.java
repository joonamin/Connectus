package social.connectus.application.rest.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OpenedPostResponse {
	private List<Long> openedPostList;
}
