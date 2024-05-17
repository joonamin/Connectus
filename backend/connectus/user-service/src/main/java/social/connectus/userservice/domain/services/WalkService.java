package social.connectus.userservice.domain.services;

import java.util.List;

import lombok.RequiredArgsConstructor;
import social.connectus.userservice.common.annotation.UseCase;
import social.connectus.userservice.application.response.MyPreferenceRouteResponse;
import social.connectus.userservice.application.response.MyWalkResponse;
import social.connectus.userservice.application.response.MyWalkResponse.WalkRecord;
import social.connectus.userservice.domain.port.client.response.MyWalkRecordResponse;
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
		List<MyWalkRecordResponse> myWalkRecordsResponse = walkPort.getMyWalkRecords(myWalkCommand.getUserId());
		List<WalkRecord> myWalkRecords = myWalkRecordsResponse.stream().map(record -> {
			return WalkRecord.builder()
				.walkId((long)record.getWalkId())
				.totalTime(record.getTotalTime())
				.totalDistance(record.getTotalDistance())
				.likeCount(record.getLikeCount())
				.trackingCount(record.getTrackingCount())
				.title(record.getTitle())
				.build();
		}).toList();
		return MyWalkResponse.builder().walkRecords(myWalkRecords).build();
	}

	@Override
	public MyPreferenceRouteResponse getMyPreferenceRoute(MyPreferenceRouteCommand myPreferenceRouteCommand) {
		List<MyPreferenceRouteResponse.Position> positions = walkPort.getMyPreferenceWalkRoutes(
			myPreferenceRouteCommand.getUserId());

		return null;
	}
}
