package social.connectus.walk.domain.command;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import social.connectus.walk.application.rest.request.CreateWalkRequest;
import social.connectus.walk.application.rest.request.PostRequestForWalk;
import social.connectus.walk.domain.model.entity.CompletedAchievement;
import social.connectus.walk.domain.model.entity.Route;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateWalkCommand {
    private Long userId;
    private String title;
    private List<Route> route;
    private Set<CompletedAchievement> completedAchievement;
    private List<PostRequestForWalk> postList;
    private int walkTime;
    private double walkDistance;   // 산책 거리
    private Long participateEvent;
    private boolean isPublic;
    private MultipartFile image;

    public static CreateWalkCommand from(CreateWalkRequest request){
        return CreateWalkCommand.builder()
                .userId(request.getUserId())
                .title(request.getTitle())
                .route(request.getRoute())
                .completedAchievement(request.getCompletedAchievement())
                .postList(request.getPostList())
                .walkTime(request.getWalkTime())
                .walkDistance(request.getWalkDistance())
                .participateEvent(request.getParticipateEvent())
                .isPublic(request.isPublic())
                .image(request.getImage())
                .build();
    }

}
