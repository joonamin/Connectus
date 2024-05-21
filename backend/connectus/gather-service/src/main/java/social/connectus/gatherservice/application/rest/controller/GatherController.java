package social.connectus.gatherservice.application.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.connectus.gatherservice.application.rest.request.CloseGatherRequest;
import social.connectus.gatherservice.application.rest.request.CreateGatherRequest;
import social.connectus.gatherservice.application.rest.request.JoinGatherRequest;
import social.connectus.gatherservice.application.rest.request.WantJoinGatherRequest;
import social.connectus.gatherservice.application.rest.response.*;
import social.connectus.gatherservice.common.exception.*;
import social.connectus.gatherservice.domain.command.CloseGatherCommand;
import social.connectus.gatherservice.domain.command.CreateGatherCommand;
import social.connectus.gatherservice.domain.model.Gather;
import social.connectus.gatherservice.domain.ports.inbound.GatherUseCase;

@RestController
@RequestMapping("/gather")
@AllArgsConstructor
public class GatherController {

    private final GatherUseCase gatherUseCase;

    @Operation(summary = "연결 확인")
    @GetMapping("/health-check")
    public String healthCheck(){
        return "It's working on gather-service!";
    }

    @Operation(summary = "모여라 작성")
    @PostMapping
    ResponseEntity<CreateGatherResponse> createGather(@RequestBody CreateGatherRequest request){
        CreateGatherResponse response = gatherUseCase.createGather(CreateGatherCommand.from(request));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "모여라 상세조회")
    @GetMapping("/{id}")
    public ResponseEntity<GetGatherResponse> getGather(@PathVariable(value="id") long gatherId) throws ResourceNotFoundException {
        GetGatherResponse getGatherResponse = gatherUseCase.getGather(gatherId);
        return ResponseEntity.ok().body(getGatherResponse);
    }

    @Operation(summary = "모여라 모집완료")
    @PatchMapping("/close")
    ResponseEntity<CloseGatherResponse> closeGather(@RequestBody CloseGatherRequest request) throws ResourceNotFoundException, InvalidHostIdException, ClosedGatherException {
        gatherUseCase.closeGather(CloseGatherCommand.from(request));
        return ResponseEntity.ok().body(CloseGatherResponse.builder().msg("Gather is closed successfully.").build());
    }

    @Operation(summary = "모여라 참여 희망")
    @PutMapping("/want_join")
    ResponseEntity<WantJoinGatherResponse> wantJoinGather(@RequestBody WantJoinGatherRequest request) throws ResourceNotFoundException, GlobalException, ClosedGatherException, AlreadyJoinedException {
        gatherUseCase.wantJoinGather(request);
        return ResponseEntity.ok().body(WantJoinGatherResponse.builder().msg("User "+request.getUserId()+" is saved as candidates successfully.").build());
    }

    @Operation(summary = "모여라 참여 확정")
    @PutMapping("/join")
    ResponseEntity<JoinGatherResponse> joinGather(@RequestBody JoinGatherRequest request) throws AlreadyJoinedException, ResourceNotFoundException, ClosedGatherException, InvalidHostIdException {
        boolean isGatherClosed = gatherUseCase.joinGather(request);
        if(isGatherClosed)
            return ResponseEntity.ok().body(JoinGatherResponse.builder().msg("User "+request.getUserId()+" is saved as joiner successfully. Gather is automatically closed!").build());
        else
            return ResponseEntity.ok().body(JoinGatherResponse.builder().msg("User "+request.getUserId()+" is saved as joiner successfully.").build());
    }

}