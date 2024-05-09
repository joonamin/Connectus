package social.connectus.walk.application.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.domain.model.VO.Position;
import social.connectus.walk.domain.model.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetWalkResponse {
    private Long walkId;
    private Long userId;
    private String title;
    private List<Position> route;
    private int walkTime;
    private double walkDistance;
    private Set<Long> likeUsers;
    private Set<Long> completedAchievement;
    private Long participateEvent;
    private Set<Long> trackingUsers;
    private boolean isPublic;

    public static GetWalkResponse from(Walk walk){
        List<Position> positions = new ArrayList<>();
        for(Route route : walk.getRoute()){
            Position position = Position.builder()
                    .latitude(route.getLatitude())
                    .longitude(route.getLongitude())
                    .build();
            positions.add(position);
        }

        return GetWalkResponse.builder()
                .walkId(walk.getId())
                .userId(walk.getUserId())
                .title(walk.getTitle())
                .route(positions)
                .walkTime(walk.getWalkTime())
                .walkDistance(walk.getWalkDistance())
                .likeUsers(walk.getLikeUsers()
                        .stream()
                        .map(LikeUser::getUserId)
                        .collect(Collectors.toSet()))
                .completedAchievement(walk.getCompletedAchievement()
                        .stream()
                        .map(CompletedAchievement::getAchievementId)
                        .collect(Collectors.toSet()))
                .participateEvent(walk.getParticipateEvent())
                .trackingUsers(walk.getTrackingUsers()
                        .stream()
                        .map(TrackingUser::getUserId)
                        .collect(Collectors.toSet()))
                .isPublic(walk.isPublic())
                .build();
    }
}
