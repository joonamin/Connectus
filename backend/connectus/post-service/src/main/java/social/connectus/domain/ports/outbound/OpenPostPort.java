package social.connectus.domain.ports.outbound;

import social.connectus.application.rest.request.OpenPostRequest;

public interface OpenPostPort {
	String insertOpenPost(OpenPostRequest request);
}
