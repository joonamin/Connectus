package social.connectus.userservice.domain.application.request;

import lombok.Data;

@Data
public class UserLoginRequest {
	private String email;
	private String password;
}
