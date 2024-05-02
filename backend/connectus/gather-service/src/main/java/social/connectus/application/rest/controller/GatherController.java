package social.connectus.application.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.connectus.application.rest.request.CloseGatherRequest;
import social.connectus.application.rest.request.WantJoinGatherRequest;
import social.connectus.application.rest.response.CloseGatherResponse;
import social.connectus.application.rest.response.CreateGatherResponse;
import social.connectus.application.rest.response.WantJoinGatherResponse;
import social.connectus.common.exception.ClosedGatherException;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.exception.InvalidHostIdException;
import social.connectus.common.exception.ResourceNotFoundException;
import social.connectus.domain.model.Gather;
import social.connectus.domain.ports.inbound.CloseGatherUseCase;
import social.connectus.domain.ports.inbound.CreateGatherUseCase;
import social.connectus.domain.ports.inbound.GetGatherUseCase;
import social.connectus.domain.ports.inbound.WantJoinGatherUseCase;

@RestController
@RequestMapping("/gather")
@AllArgsConstructor
public class GatherController {

    private final CreateGatherUseCase createGatherUseCase;
    private final CloseGatherUseCase closeGatherUseCase;
    private final GetGatherUseCase getGatherUseCase;
    private final WantJoinGatherUseCase wantJoinGatherUseCase;

    @GetMapping("/health_check")
    public String health_check(){
        return "It's working on gather-service!";
    }

    @PostMapping
    ResponseEntity<CreateGatherResponse> createGather(@RequestBody Gather gather){
        createGatherUseCase.createGather(gather);
        return ResponseEntity.ok().body(CreateGatherResponse.builder().id(gather.getId()).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gather> getGather(@PathVariable(value="id") long gatherId) throws ResourceNotFoundException {
        Gather gather = getGatherUseCase.getGather(gatherId);
        return ResponseEntity.ok().body(gather);
    }

    @PatchMapping("/close")
    ResponseEntity<CloseGatherResponse> closeGather(@RequestBody CloseGatherRequest closeGatherRequest) throws ResourceNotFoundException, InvalidHostIdException, ClosedGatherException{
        closeGatherUseCase.closeGather(closeGatherRequest);
        return ResponseEntity.ok().body(CloseGatherResponse.builder().msg("Gather is closed successfully.").build());
    }

    @PatchMapping("/want_join")
    ResponseEntity<WantJoinGatherResponse> wantJoinGather(@RequestBody WantJoinGatherRequest wantJoinGatherRequest) throws ResourceNotFoundException, GlobalException, ClosedGatherException {
        wantJoinGatherUseCase.wantJoinGather(wantJoinGatherRequest);
        return ResponseEntity.ok().body(WantJoinGatherResponse.builder().msg("User "+wantJoinGatherRequest.getUserId()+" is saved as candidates successfully.").build());
    }

}