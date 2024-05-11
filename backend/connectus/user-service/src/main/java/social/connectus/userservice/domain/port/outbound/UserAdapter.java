package social.connectus.userservice.domain.port.outbound;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import social.connectus.userservice.common.aop.annotation.YetNotImplemented;
import social.connectus.userservice.common.exception.FailedToRegisterUserException;
import social.connectus.userservice.common.exception.LoginFailedException;
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
			.build();
		userRepository.save(user);
	}

	@Override
	public User loginUser(UserLoginCommand command) throws LoginFailedException {
		User user = userRepository.findByEmail(command.getEmail())
			.orElseThrow(
				() -> new LoginFailedException("wrong email!!"));

		if (!passwordEncoder.matches(command.getPassword(), user.getPassword())) {
			throw new LoginFailedException("wrong password!!");
		}

		return user;
	}

	@Override
	@YetNotImplemented
	public void logoutUser(UserLogoutCommand command) {
	}
}
