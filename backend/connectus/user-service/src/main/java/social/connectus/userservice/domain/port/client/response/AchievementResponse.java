package social.connectus.userservice.domain.port.client.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AchievementResponse {
	private String field;
	private int goal;
	private String title;
	private String content;
	private String imageUrl;
	private int reward;
}
