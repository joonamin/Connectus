package social.connectus.userservice.domain.port.outbound;

import java.util.Collections;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import social.connectus.userservice.common.aop.annotation.YetNotImplemented;
import social.connectus.userservice.common.exception.LoginFailedException;
import social.connectus.userservice.common.exception.NotFoundException;
import social.connectus.userservice.domain.application.response.OpenedPostResponse;
import social.connectus.userservice.domain.model.entity.User;
import social.connectus.userservice.domain.port.inbound.command.UserLoginCommand;
import social.connectus.userservice.domain.port.inbound.command.UserLogoutCommand;
import social.connectus.userservice.domain.port.inbound.command.UserRegisterCommand;
import social.connectus.userservice.domain.port.outbound.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserPort {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void registerUser(UserRegisterCommand command) {
		// command -> User entity
		String encryptedPassword = passwordEncoder.encode(command.getPassword());
		User user = User.builder()
			.name(command.getName())
			.email(command.getEmail())
			.point(0)
			.accomplishedAchievements(Collections.EMPTY_LIST)
			.chatRoomIds(Collections.EMPTY_LIST)
			.postHistory(Collections.EMPTY_LIST)
			.profileImageUrl("default")
			.birthday(command.getBirthday())
			.password(encryptedPassword)
			.nickname(command.getNickname())
			.phoneNumber(command.getPhoneNumber())
			.build();
		userRepository.save(user);
	}

	@Override
	public User loginUser(UserLoginCommand command) throws LoginFailedException {
		return userRepository.findByEmailAndRawPassword(command.getEmail(), command.getPassword())
			.orElseThrow(
				() -> new LoginFailedException("wrong password or wrong email, check your account information"));
	}

	@Override
	@YetNotImplemented
	public void logoutUser(UserLogoutCommand command) {
	}

	@Override
	public void updateOpenedPosts(Long userId, Long postId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user doesn't exists"));
		List<Long> openedPosts = user.getPostHistory();
		openedPosts.add(postId);
		user.updateOpenedPosts(openedPosts);
		userRepository.save(user);
	}

	@Override
	public OpenedPostResponse getOpenedPost(Long userId) {
		return new OpenedPostResponse(userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user doesn't exists")).getPostHistory());
	}
}
