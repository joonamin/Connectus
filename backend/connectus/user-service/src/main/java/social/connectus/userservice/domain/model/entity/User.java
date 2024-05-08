package social.connectus.userservice.domain.model.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.userservice.common.type.Achievement;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	private String email;

	private String password;

	private String phoneNumber;

	private String nickname;

	private String name;

	private LocalDate birthday;

	private Integer point;

	@ElementCollection
	private List<Long> postHistory; // 읽었던 방명록 리스트

	private String profileImageUrl;

	@ElementCollection
	private List<Long> chatRoomIds; // 참여하고 있는 채팅방 아이디

	@ElementCollection
	private List<Achievement> accomplishedAchievements; // 성취한 업적 리스트 아이디

	private Statistics statistics;
}
