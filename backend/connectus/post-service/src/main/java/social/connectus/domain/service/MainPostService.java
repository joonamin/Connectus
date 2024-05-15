package social.connectus.domain.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import social.connectus.application.rest.response.MainPostResponse;
import social.connectus.common.annotation.UseCase;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.exception.ParameterNotFoundException;
import social.connectus.domain.ports.inbound.MainPostUseCase;
import social.connectus.domain.ports.outbound.MainPostPort;

@UseCase
@RequiredArgsConstructor
public class MainPostService implements MainPostUseCase {
	private final MainPostPort mainPostPort;
	@Override
	public List<MainPostResponse> mainPost(List<Long> postIdList) throws GlobalException {
		try {
			if(postIdList == null) {
				throw new ParameterNotFoundException("postId");
			}
		} catch(Exception e) {
			throw new GlobalException("MainPostService : " + e.getMessage());
		}

		return mainPostPort.getMainPost(postIdList);
	}
}
