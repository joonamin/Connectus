package social.connectus.domain.service.command;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import social.connectus.application.rest.request.PostRequestDto;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class InsertPostCommand {
	private String content;
	private Long walkId;
	private Long authorId;
	private String imageUrl;
	public static InsertPostCommand from(PostRequestDto dto){
		return InsertPostCommand.builder()
			.content(dto.getContent())
			.authorId(dto.getAuthorId())
			.walkId(dto.getWalkId())
			.build();
	}
}
