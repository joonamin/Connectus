package social.connectus.userservice.domain.application.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterRequest {

	private String email;
	private String password;
	private String phoneNumber;
	private String nickname;
	private String name;
	private LocalDate birthday;
	private String imageUrl;
}
