package social.connectus.domain.service.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import social.connectus.application.rest.request.PostRequestDto;
import social.connectus.application.rest.request.SpotDto;
import social.connectus.domain.model.RDBMS.Post;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostSpotCommand {
	private List<SpotDto> spotList;
}
