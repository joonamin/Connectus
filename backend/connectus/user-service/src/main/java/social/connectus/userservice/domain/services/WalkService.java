package social.connectus.userservice.domain.services;

import lombok.RequiredArgsConstructor;
import social.connectus.userservice.common.annotation.UseCase;
import social.connectus.userservice.domain.application.response.MyWalkResponse;
import social.connectus.userservice.domain.port.external.port.WalkPort;
import social.connectus.userservice.domain.port.inbound.WalkUseCase;
import social.connectus.userservice.domain.port.inbound.command.MyWalkCommand;

@UseCase
@RequiredArgsConstructor
public class WalkService implements WalkUseCase {

	private final WalkPort walkPort;

	@Override
	public MyWalkResponse getMyWalk(MyWalkCommand myWalkCommand) {




	}
}
