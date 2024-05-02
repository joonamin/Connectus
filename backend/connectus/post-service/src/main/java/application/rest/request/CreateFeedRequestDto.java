package application.rest.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateFeedRequestDto {
	private Long walkId;
	private List<PostRequestDto> postList;
}
