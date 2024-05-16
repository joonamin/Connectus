package social.connectus.userservice.domain.port.outbound;

import java.util.Collections;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import social.connectus.userservice.common.aop.annotation.YetNotImplemented;
import social.connectus.userservice.common.exception.FailedToLoginException;
import social.connectus.userservice.common.exception.FailedToRegisterUserException;
import social.connectus.userservice.common.exception.NotFoundException;
import social.connectus.userservice.domain.application.response.OpenedPostResponse;
import social.connectus.userservice.domain.model.entity.User;
import social.connectus.userservice.domain.port.inbound.command.UserLoginCommand;
import social.connectus.userservice.domain.port.inbound.command.UserLogoutCommand;
import social.connectus.userservice.domain.port.inbound.command.UserRegisterCommand;
import social.connectus.userservice.domain.port.outbound.repository.UserRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserAdapter implements UserPort {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void registerUser(UserRegisterCommand command) {
		// command -> User entity
		if (userRepository.findByEmail(command.getEmail()).isPresent()) {
			throw new FailedToRegisterUserException("이미 회원가입이 된 유저입니다");
		}

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
			.walkCount(0)
			.postCount(0)
			.build();
		userRepository.save(user);
	}

	@Override
	public User loginUser(UserLoginCommand command) throws FailedToLoginException {
		User user = userRepository.findByEmail(command.getEmail())
			.orElseThrow(
				() -> new FailedToLoginException("wrong email!!"));

		if (!passwordEncoder.matches(command.getPassword(), user.getPassword())) {
			throw new FailedToLoginException("wrong password!!");
		}

		return user;
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

	@Override
	public String getUserNickname(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user doesn't exists")).getNickname();
	}

	@Override
	public String getUserNickname(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user doesn't exists")).getNickname();
	}
}
