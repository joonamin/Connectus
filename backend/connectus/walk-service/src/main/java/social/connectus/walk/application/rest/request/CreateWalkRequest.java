package social.connectus.walk.application.rest.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import social.connectus.walk.domain.model.entity.Route;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateWalkRequest {
    private Long userId;
    private String title;
    private List<Route> route;
    private List<PostRequestForWalk> postList;
    private int walkTime;
    private double walkDistance;   // 산책 거리
    private Long participateEvent;
    private boolean isPublic;
    private MultipartFile image;
}
