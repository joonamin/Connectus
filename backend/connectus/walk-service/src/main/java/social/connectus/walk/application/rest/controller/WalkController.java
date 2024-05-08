package social.connectus.walk.application.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.connectus.walk.application.rest.request.CreateWalkRequest;
import social.connectus.walk.application.rest.request.RouteLikeRequest;
import social.connectus.walk.application.rest.response.CreateWalkResponse;
import social.connectus.walk.application.rest.response.GetWalkResponse;
import social.connectus.walk.application.rest.response.GetWalksByUserResponse;
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
    public String feignHealthCheck() {
        // TODO: 다른 모든 서비스의 healthCheck를 받아 json 객체로 반환
        return walkUseCase.feignHealthCheck();
    }

    @GetMapping("/{walkId}")
    public ResponseEntity<GetWalkResponse> getWalk(@PathVariable long walkId){
        GetWalkResponse response = GetWalkResponse.from(walkUseCase.getWalkById(walkId));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<GetWalksByUserResponse> getWalkByUser(@PathVariable long userId){
        GetWalksByUserResponse response = GetWalksByUserResponse.from(walkUseCase.getWalkByUser(userId));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateWalkResponse> createWalk(@RequestBody CreateWalkRequest request){
        // TODO: request 검증
        return ResponseEntity.ok(walkUseCase.createWalk(CreateWalkCommand.from(request)));
    }

    @PatchMapping("route-like")
    public ResponseEntity<String> routeLike(@RequestBody RouteLikeRequest request){
        walkUseCase.routeLike(RouteLikeCommand.from(request));
        return ResponseEntity.ok("Like is successfully registered.");
    }

}
