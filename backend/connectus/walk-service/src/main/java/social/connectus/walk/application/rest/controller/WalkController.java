package social.connectus.walk.application.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.connectus.walk.application.rest.request.CreateWalkRequest;

@RestController
@RequestMapping("/walk")
public class WalkController {
    @GetMapping("/health_check")
    public String health_check(){
        return "It's walking on Walk-Service!";
    }

//    @PostMapping
//    public ResponseEntity<CreateWalkResponse> createWalk(CreateWalkRequest request){
//        CreateWalkResponse response = WalkUseCase.createWalk(CreateWalkCommand.from(request));
//        return ResponseEntity.ok(response);
//    }
}
