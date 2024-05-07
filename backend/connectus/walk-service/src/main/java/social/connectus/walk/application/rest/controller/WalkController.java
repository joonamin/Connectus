package social.connectus.walk.application.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.connectus.walk.application.rest.request.CreateWalkRequest;
import social.connectus.walk.application.rest.response.CreateWalkResponse;
import social.connectus.walk.domain.command.CreateWalkCommand;
import social.connectus.walk.domain.ports.inbound.WalkUseCase;

@RestController
@RequestMapping("/walk")
@RequiredArgsConstructor
public class WalkController {

    private final WalkUseCase walkUseCase;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "It's walking on Walk-Service!";
    }

    @GetMapping("/feign-health-check")
    public String feignHealthCheck() { return walkUseCase.feignHealthCheck();}

    @PostMapping
    public ResponseEntity<CreateWalkResponse> createWalk(@RequestBody CreateWalkRequest request){
        // TODO: request 검증
//        List<Long> positionIdList = new ArrayList<>();
//        positionIdList.add(1L);
//        positionIdList.add(2L);
//        positionIdList.add(3L);
//        Route route = new Route(positionIdList);

        CreateWalkCommand command = CreateWalkCommand.builder()
                .userId(request.getUserId())
                .title(request.getTitle())
                .completedAchievement(request.getCompletedAchievement())
                .route(request.getRoute())
                .walkTime(request.getWalkTime())
                .walkDistance(request.getWalkDistance())
                .participateEvent(request.getParticipateEvent())
                .build();
        CreateWalkResponse response = walkUseCase.createWalk(command);
        return ResponseEntity.ok(response);
    }

}
