package social.connectus.domain.service.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import social.connectus.application.rest.request.CreateFeedRequestDto;
import social.connectus.application.rest.request.PostRequestDto;
import social.connectus.domain.model.RDBMS.Post;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostPositionCommand {
	private Long domainId;
	private Double longitude;
	private Double latitude;
	private String type;

	public static PostPositionCommand from(Post post, PostRequestDto postRequestDto) {
		return PostPositionCommand.builder()
			.domainId(post.getId())
			.longitude(postRequestDto.getLongitude())
			.latitude(postRequestDto.getLatitude())
			.type("POST")
			.build();
	}
}
