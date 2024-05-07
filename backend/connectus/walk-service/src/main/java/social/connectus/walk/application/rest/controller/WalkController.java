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

    @GetMapping("/health_check")
    public String health_check(){
        return "It's walking on Walk-Service!";
    }

    @PostMapping
    public ResponseEntity<CreateWalkResponse> createWalk(CreateWalkRequest request){
        // TODO: request 검증

        CreateWalkResponse response = walkUseCase.createWalk(CreateWalkCommand.from(request));
        return ResponseEntity.ok(response);
    }

}
