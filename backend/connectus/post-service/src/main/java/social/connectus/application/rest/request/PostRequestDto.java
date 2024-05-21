package social.connectus.application.rest.request;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostRequestDto {
	private String content;
	private Long walkId;
	private Long authorId;
	private String imageUrl;
	private Double longitude;
	private Double latitude;
}
