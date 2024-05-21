package social.connectus.userservice.common.type;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import social.connectus.userservice.domain.port.outbound.command.RefreshAchievementToUserCommand;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
@AllArgsConstructor
public enum Achievement {
	WALK_1(RefreshAchievementToUserCommand.Field.WALK_COUNT, 1, "위대한 첫걸음", "첫 산책을 나갔어요.", "", 1),
	WALK_2(RefreshAchievementToUserCommand.Field.WALK_COUNT, 5, "길위의 탐험가", "산책을 5번 나가보세요.", "", 5),
	WALK_3(RefreshAchievementToUserCommand.Field.WALK_COUNT, 10, "노련한 산책가", "산책을 10번 나가보세요.", "", 10),
	WALK_4(RefreshAchievementToUserCommand.Field.WALK_COUNT, 30, "길 위의 마스터", "산책을 30번 나가보세요.", "", 30),
	WALK_5(RefreshAchievementToUserCommand.Field.WALK_COUNT, 50, "산책의 달인", "산책을 50번 나가보세요", "", 50),

	POST_1(RefreshAchievementToUserCommand.Field.POST_COUNT, 1, "초보 작가", "방명록 1개를 작성하세요.", "", 1),
	POST_2(RefreshAchievementToUserCommand.Field.POST_COUNT, 10, "열정적인 필자", "방명록 10개를 작성하세요.", "", 10),
	POST_3(RefreshAchievementToUserCommand.Field.POST_COUNT, 30, "경험있는 이야기꾼", "방명록 30개를 작성하세요.", "", 30);

	// EVENT_1();
	private final RefreshAchievementToUserCommand.Field field;
	private final float goal;
	private final String title;
	private final String content;
	private final String imageUrl;
	private final int reward;
}
