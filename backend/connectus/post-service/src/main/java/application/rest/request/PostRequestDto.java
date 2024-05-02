package application.rest.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostRequestDto {
	private String content;
	private Long authorId;
	private CoordinateRequestDto coordinateRequestDto;
	private MultipartFile image;
}
