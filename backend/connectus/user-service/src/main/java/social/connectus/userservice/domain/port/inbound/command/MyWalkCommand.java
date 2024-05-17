package social.connectus.userservice.domain.port.inbound.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.userservice.application.request.MyWalkRequest;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyWalkCommand {
	Long userId;

	public static MyWalkCommand from(MyWalkRequest request) {
		return MyWalkCommand.builder()
			.userId(request.getUserId())
			.build();
	}
}
