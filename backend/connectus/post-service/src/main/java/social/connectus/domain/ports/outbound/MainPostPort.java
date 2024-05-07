package social.connectus.domain.ports.outbound;

import java.util.List;

import social.connectus.application.rest.response.MainPostResponse;

public interface MainPostPort {
	List<MainPostResponse> getMainPost(List<Long> postIdList);
}
