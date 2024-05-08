package social.connectus.userservice.domain.port.outbound;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import social.connectus.userservice.common.type.Achievement;
import social.connectus.userservice.domain.model.entity.User;
import social.connectus.userservice.domain.port.inbound.command.UserLoginCommand;
import social.connectus.userservice.domain.port.inbound.command.UserRegisterCommand;
import social.connectus.userservice.domain.port.outbound.command.RefreshAchievementToUserCommand;
import social.connectus.userservice.domain.port.outbound.command.UserToRefreshAchievementCommand;
import social.connectus.userservice.domain.port.outbound.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserPort {

	private final UserRepository userRepository;

	@Override
	@Transactional
	public void registerUser(UserRegisterCommand command) {
		// command -> User entity
		User user = User.builder()
			.name(command.getName())
			.email(command.getEmail())
			.point(0)
			.accomplishedAchievements(Collections.EMPTY_LIST)
			.chatRoomIds(Collections.EMPTY_LIST)
			.postHistory(Collections.EMPTY_LIST)
			.profileImageUrl("default")
			.birthday(command.getBirthday())
			.password(command.getPassword())
			.nickname(command.getNickname())
			.phoneNumber(command.getPhoneNumber())
			.postCount(0)
			.walkCount(0)
			.build();

		userRepository.save(user);
	}

	@Override
	public Optional<User> loginUser(UserLoginCommand command) {
		return Optional.empty();
	}

}
