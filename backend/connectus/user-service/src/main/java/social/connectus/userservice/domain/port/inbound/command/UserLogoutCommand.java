package social.connectus.userservice.domain.port.inbound.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import social.connectus.userservice.domain.application.request.UserLogoutRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLogoutCommand {
	private String accessToken;

	public static UserLogoutCommand from(UserLogoutRequest request) {
		// todo: jwt 검증 로직 필요, claim 추출, request의 accessToken과 email이 일치하는지..
		return UserLogoutCommand.builder()
			.accessToken(request.getAccessToken())
			.build();
	}
}
