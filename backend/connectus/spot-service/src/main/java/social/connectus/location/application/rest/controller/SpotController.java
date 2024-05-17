package social.connectus.location.application.rest.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.lang.NullArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.connectus.location.application.rest.request.*;
import social.connectus.location.application.rest.response.CreateSpotResponse;
import social.connectus.location.application.rest.response.FindNearbyElementResponse;
import social.connectus.location.application.rest.response.GetSpotResponse;
import social.connectus.location.domain.command.CreateSpotCommand;
import social.connectus.location.domain.command.FindNearbyElementCommand;
import social.connectus.location.domain.command.GetSpotCommand;
import social.connectus.location.domain.ports.inbound.SpotUseCase;

import java.util.List;

@RestController
@RequestMapping("/spot")
@AllArgsConstructor
public class SpotController {

    private final SpotUseCase spotUseCase;

    @GetMapping("/health-check")
    public String health_check(){
        return "It's working on spot-service!";
    }

    @PostMapping("/findNearby")
    public FindNearbyElementResponse findNearbyElement(@RequestBody FindNearbyElementRequest request) {
        FindNearbyElementCommand command = new FindNearbyElementCommand(request.getLongitude(), request.getLongitude());
        return spotUseCase.findNearbyElement(command);
    }

    @PostMapping("/insert")
    public ResponseEntity<CreateSpotResponse> createSpot(@RequestBody CreateSpotRequest request){
        for(SpotDto spot : request.getSpotList()){
            if(spot.getLongitude()==null || spot.getLatitude()==null){
                throw new NullArgumentException("Parameter doesn't allow Null.");
            }
        }
        CreateSpotCommand command = CreateSpotCommand.from(request);
        List<Long> list = spotUseCase.createSpot(command);
        return ResponseEntity.ok(CreateSpotResponse.builder().spotIdList(list).build());
    }

    @PostMapping("/get")
    public ResponseEntity<GetSpotResponse> getSpotList(@RequestBody GetSpotRequest request){
        GetSpotResponse response = GetSpotResponse.builder()
                .spotList(spotUseCase.getSpot(GetSpotCommand.from(request)))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateSpotList(@RequestBody CreateSpotRequest request){
        return ResponseEntity.ok("Not used API.");
//        CreateSpotResponse response = CreateSpotResponse.builder()
//                .spotIdList(spotUseCase.updateSpot(CreateSpotCommand.from(request)))
//                .build();
//        return ResponseEntity.ok(response);
    }
}
