package social.connectus.walk.application.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.connectus.walk.application.rest.request.CreateWalkRequest;
import social.connectus.walk.application.rest.request.RouteLikeRequest;
import social.connectus.walk.application.rest.response.CreateWalkResponse;
import social.connectus.walk.domain.command.CreateWalkCommand;
import social.connectus.walk.domain.command.RouteLikeCommand;
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

    @PatchMapping("route-like")
    public ResponseEntity<String> routeLike(@RequestBody RouteLikeRequest request){
//        ResponseStatus routeLike(Long walkId, Long userId);
        walkUseCase.routeLike(RouteLikeCommand.from(request));
        return ResponseEntity.ok("Like is successfully registered.");
    }

}
