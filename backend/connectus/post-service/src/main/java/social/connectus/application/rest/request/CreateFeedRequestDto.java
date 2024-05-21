package social.connectus.application.rest.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateFeedRequestDto {
	private Long walkId;
	private List<PostRequestDto> postList;
}
