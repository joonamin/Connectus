package social.connectus.userservice.domain.port.inbound.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import social.connectus.userservice.application.request.MyPreferencePostRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyPreferencePostCommand {
	private Long userId;

	public static MyPreferencePostCommand from(MyPreferencePostRequest request) {
		return MyPreferencePostCommand.builder()
			.userId(request.getUserId())
			.build();
	}

}
