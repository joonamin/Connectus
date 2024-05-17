package social.connectus.userservice.domain.application.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserPositionRequest {
	Long userId;
	Double latitude;
	Double longitude;
}
