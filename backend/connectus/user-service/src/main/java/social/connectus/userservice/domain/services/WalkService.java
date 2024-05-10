package social.connectus.userservice.domain.services;

import java.util.List;

import lombok.RequiredArgsConstructor;
import social.connectus.userservice.common.annotation.UseCase;
import social.connectus.userservice.domain.application.response.MyPreferenceRouteResponse;
import social.connectus.userservice.domain.application.response.MyWalkResponse;
import social.connectus.userservice.domain.application.response.MyWalkResponse.WalkRecord;
import social.connectus.userservice.domain.port.client.WalkClient;
import social.connectus.userservice.domain.port.external.port.WalkPort;
import social.connectus.userservice.domain.port.inbound.WalkUseCase;
import social.connectus.userservice.domain.port.inbound.command.MyPreferenceRouteCommand;
import social.connectus.userservice.domain.port.inbound.command.MyWalkCommand;

@UseCase
@RequiredArgsConstructor
public class WalkService implements WalkUseCase {

	private final WalkPort walkPort;


	@Override
	public MyWalkResponse getMyWalk(MyWalkCommand myWalkCommand) {
		List<WalkRecord> myWalkRecords = walkPort.getMyWalkRecords(myWalkCommand.getUserId());
		return MyWalkResponse.builder().walkRecords(myWalkRecords).build();
	}

	@Override
	public MyPreferenceRouteResponse getMyPreferenceRoute(MyPreferenceRouteCommand myPreferenceRouteCommand) {
		List<MyPreferenceRouteResponse.Position> positions = walkPort.getMyPreferenceWalkRoutes(
			myPreferenceRouteCommand.getUserId());

		return null;
	}
}
