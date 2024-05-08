package social.connectus.userservice.domain.application.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLogoutRequest {
	private String email;
	private String accessToken;
}
