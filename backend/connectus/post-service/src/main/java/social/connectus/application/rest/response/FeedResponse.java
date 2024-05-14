package social.connectus.application.rest.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import social.connectus.domain.model.RDBMS.Post;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedResponse {
	private Long walkId;
	private Long authorId;
	private String authorName;
	List<DetailPostResponse> postList;

	public static FeedResponse from(List<Post> postList, String authorName) {
		Post postHead = postList.get(0);
		List<DetailPostResponse> detailPostResponseList = new ArrayList<>();
		for(Post post : postList) {
			detailPostResponseList.add(DetailPostResponse.samplePostFrom(post,authorName));
		}
		return FeedResponse.builder()
			.walkId(postHead.getWalkId())
			.authorId(postHead.getAuthorId())
			.authorName(authorName)
			.postList(detailPostResponseList)
			.build();
	}
}
