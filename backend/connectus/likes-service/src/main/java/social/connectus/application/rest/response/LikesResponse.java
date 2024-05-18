package social.connectus.application.rest.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikesResponse {
	List<Long> likedPostList;
}
