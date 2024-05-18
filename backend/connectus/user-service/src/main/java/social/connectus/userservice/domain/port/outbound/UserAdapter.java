package social.connectus.userservice.domain.port.outbound;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import social.connectus.userservice.application.request.CreateUserPositionRequest;
import social.connectus.userservice.application.request.InsertPostRequest;
import social.connectus.userservice.application.response.ChangePositionResponse;
import social.connectus.userservice.application.response.LikeResponse;
import social.connectus.userservice.application.response.OpenedPostResponse;
import social.connectus.userservice.application.response.PointResponse;
import social.connectus.userservice.application.response.PostResponse;
import social.connectus.userservice.application.response.UserInfoResponse;
import social.connectus.userservice.application.response.UserResponseForPost;
import social.connectus.userservice.common.aop.annotation.YetNotImplemented;
import social.connectus.userservice.common.exception.FailedToChagePointException;
import social.connectus.userservice.common.exception.FailedToLoginException;
import social.connectus.userservice.common.exception.FailedToRegisterUserException;
import social.connectus.userservice.common.exception.NotFoundException;
import social.connectus.userservice.domain.command.PointChangeCommand;
import social.connectus.userservice.domain.model.entity.User;
import social.connectus.userservice.domain.port.client.LikesClient;
import social.connectus.userservice.domain.port.client.PostClient;
import social.connectus.userservice.domain.port.client.SpotClient;
import social.connectus.userservice.domain.port.inbound.command.*;
import social.connectus.userservice.domain.port.client.WalkClient;
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
	private final SpotClient spotClient;
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
	public void updateOpenedPost(Long userId, Long postId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user doesn't exists"));
		List<Long> openedPosts = user.getPostHistory();
		openedPosts.add(postId);
		user.updateOpenedPosts(openedPosts);
		userRepository.save(user);
	}

	@Override
	public void updateOpenedPosts(Long userId, List<Long> postIdList) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user doesn't exists"));
		List<Long> openedPosts = user.getPostHistory();
		openedPosts.addAll(postIdList);
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
	public ChangePositionResponse insertUserPosition(CreateUserPositionRequest request) {
		User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new NotFoundException("user doesn't exists"));
		if(user.getSpotId()!=null){
			throw new RuntimeException("User already has spot.");
		}
		SpotIdListDto dto = spotClient.insertUserPosition(CreateUserPositionCommand.from(request));
		ChangePositionResponse resp = ChangePositionResponse.from(dto);
		user.changeSpotId(resp.getSpotId());
		userRepository.save(user);
		return resp;
	}

	@Override
	public ChangePositionResponse updateUserPosition(CreateUserPositionRequest request) {
		User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new NotFoundException("user doesn't exists"));
		SpotIdListDto dto = spotClient.updateUserPosition(UpdateUserPositionCommand.from(request, user.getSpotId()));
		ChangePositionResponse resp = ChangePositionResponse.from(dto);
		user.changeSpotId(resp.getSpotId());
		userRepository.save(user);
		return resp;
	}

	@Override
	public void deleteUserPosition(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user doesn't exists"));
		spotClient.deleteUserPosition(SpotIdListDto.builder()
						.spotIdList(Arrays.asList(user.getSpotId()))
				.build());
		user.changeSpotId(null);
		userRepository.save(user);
	}

	@Override
	public UserInfoResponse getUserInfo(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("user doesn't exists"));
		return UserInfoResponse.from(user);
	}

	@Override
	public UserResponseForPost getUserResponseForPost(Long userId) {
		return UserResponseForPost.from(userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user doesn't exists")));
	}

	@Override
	public PointResponse increasePoint(PointChangeCommand command) {
		User user = userRepository.findById(command.getUserId()).orElseThrow(() -> new NotFoundException("user doesn't exists"));
		user.changeUserPoint(command.getChangeValue());
		userRepository.save(user);
		return PointResponse.builder()
				.currentPoint(user.getPoint())
				.userId(user.getUserId())
				.build();
	}

	@Override
	public PointResponse decreasePoint(PointChangeCommand command) {
		User user = userRepository.findById(command.getUserId()).orElseThrow(() -> new NotFoundException("user doesn't exists"));
		if(user.getPoint() - command.getChangeValue() < 0)
			throw new FailedToChagePointException("point cannot be less than zero.");
		else {
            user.changeUserPoint(command.getChangeValue() * -1);
            userRepository.save(user);
        }
		return PointResponse.builder()
				.currentPoint(user.getPoint())
				.userId(user.getUserId())
				.build();
	}


	@Override
	public String updateAvatar(Long userId, String imageUrl) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user doesn't exists"));
		user.updateAvatar(imageUrl);
		return "avatar update!";
	}
	@Override
	public String insertPostHistory(InsertPostRequest request) {
		User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new NotFoundException("user doesn't exists"));
		int hasPoint = user.getPoint();
		System.out.println(hasPoint);
		if(hasPoint <= 1) {
			return "not enough point";
		}

		List<Long> openedPostList = user.getPostHistory();
		openedPostList.add(request.getPostId());
		user.usePoint(1);
		user.updateOpenedPosts(openedPostList);
		userRepository.save(user);
		return "save completed";
	}
}
