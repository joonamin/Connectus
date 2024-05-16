package social.connectus.walk.application.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AchievementResponse {
    String achievementCode;
    String title;
    String content;
    String imageUrl;
    int reward;
}
