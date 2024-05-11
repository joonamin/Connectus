package social.connectus.application.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import social.connectus.common.type.Type;

@AllArgsConstructor
@Getter
public class LikeRequest {
	private Long userId;
	private Long domainId;
	private Type type;
}
