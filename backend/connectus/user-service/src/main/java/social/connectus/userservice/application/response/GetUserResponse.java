package social.connectus.userservice.application.response;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.*;
import social.connectus.userservice.common.type.Achievement;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserResponse {
    private Long userId;

    private String email;

    private String password;

    private String phoneNumber;

    private String nickname;

    private String name;
    private LocalDate birthday;
    private int point;
    private List<Long> postHistory; // 읽었던 방명록 리스트
    private String avatarImageUrl;
    private List<Long> chatRoomIds; // 참여하고 있는 채팅방 아이디
    private List<Achievement> accomplishedAchievements; // 성취한 업적 리스트 아이디
    private int walkCount;
    private int postCount;
}
