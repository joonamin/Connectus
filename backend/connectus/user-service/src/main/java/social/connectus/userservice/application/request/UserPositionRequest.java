package social.connectus.userservice.application.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPositionRequest {
	Long userId;
	Double latitude;
	Double longitude;
}
