package social.connectus.application.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.connectus.application.rest.response.CreateGatherResponse;
import social.connectus.domain.model.Gather;
import social.connectus.domain.ports.inbound.CreateGatherUseCase;

@RestController
@RequestMapping("/gather")
@AllArgsConstructor
public class GatherController {

    private final CreateGatherUseCase createGatherUseCase;
    @GetMapping("/health_check")
    public String health_check(){
        return "It's working on gather-service!";
    }

    @PostMapping
    ResponseEntity<CreateGatherResponse> createGather(@RequestBody Gather gather){
        createGatherUseCase.createGather(gather);
        return ResponseEntity.ok().body(CreateGatherResponse.builder().id(gather.getId()).build());
    }
}