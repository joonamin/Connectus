package social.connectus.walk.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.application.rest.request.GetAchievementsRequest;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAchievementsCommand {
    private Long userId;
    private String jsonOfClientData;

    public static GetAchievementsCommand from(GetAchievementsRequest request){
        return GetAchievementsCommand.builder()
                .userId(request.getUserId())
                .jsonOfClientData(request.getJsonOfClientData())
                .build();

    }
}
