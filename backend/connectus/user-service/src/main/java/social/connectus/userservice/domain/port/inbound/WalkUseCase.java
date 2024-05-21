package social.connectus.userservice.domain.port.inbound;

import social.connectus.userservice.application.response.MyPreferenceRouteResponse;
import social.connectus.userservice.application.response.MyWalkResponse;
import social.connectus.userservice.domain.port.inbound.command.MyPreferenceRouteCommand;
import social.connectus.userservice.domain.port.inbound.command.MyWalkCommand;

public interface WalkUseCase {

	MyWalkResponse getMyWalk(MyWalkCommand myWalkCommand);

	MyPreferenceRouteResponse getMyPreferenceRoute(MyPreferenceRouteCommand myPreferenceRouteCommand);
}
