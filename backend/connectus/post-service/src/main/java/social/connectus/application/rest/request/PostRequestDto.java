package social.connectus.application.rest.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostRequestDto {
	private String content;
	private Long walkId;
	private Long authorId;
	private String imageUrl;
}
