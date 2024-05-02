package application.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateCommentRequestDto {
	private String content;
	private Long authorId;
}
