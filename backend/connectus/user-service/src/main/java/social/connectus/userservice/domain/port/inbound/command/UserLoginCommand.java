package social.connectus.userservice.domain.port.inbound.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.userservice.application.request.UserLoginRequest;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginCommand {
	private String email;
	private String password;

	public static UserLoginCommand from(UserLoginRequest request) {
		return UserLoginCommand.builder()
			.email(request.getEmail())
			.password(request.getPassword())
			.build();
	}

}
