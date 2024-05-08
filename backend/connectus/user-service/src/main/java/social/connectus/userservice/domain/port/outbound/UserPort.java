package social.connectus.userservice.domain.port.outbound;

import java.util.List;
import java.util.Optional;

import social.connectus.userservice.common.type.Achievement;
import social.connectus.userservice.domain.model.entity.User;
import social.connectus.userservice.domain.port.inbound.command.UserLoginCommand;
import social.connectus.userservice.domain.port.inbound.command.UserRegisterCommand;
import social.connectus.userservice.domain.port.outbound.command.RefreshAchievementToUserCommand;
import social.connectus.userservice.domain.port.outbound.command.UserToRefreshAchievementCommand;

public interface UserPort {

	void registerUser(UserRegisterCommand command);

	Optional<User> loginUser(UserLoginCommand command);
}
