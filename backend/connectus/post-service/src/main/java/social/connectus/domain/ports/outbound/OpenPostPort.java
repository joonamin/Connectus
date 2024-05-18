package social.connectus.domain.ports.outbound;

import social.connectus.application.rest.request.OpenPostRequest;
import social.connectus.application.rest.response.PointResponse;

public interface OpenPostPort {
	PointResponse insertOpenPost(OpenPostRequest request);
}
