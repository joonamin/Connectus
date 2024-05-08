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
    
    // TODO: getWalk 작성

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
