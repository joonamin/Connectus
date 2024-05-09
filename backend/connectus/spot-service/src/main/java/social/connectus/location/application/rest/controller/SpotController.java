package social.connectus.location.application.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import social.connectus.location.application.rest.request.FindNearbyElementRequest;
import social.connectus.location.domain.command.FindNearbyElementCommand;
import social.connectus.location.domain.ports.inbound.SpotUseCase;

@RestController
@RequestMapping("/spot")
@AllArgsConstructor
public class SpotController {

    @Autowired
    private final SpotUseCase gatherUseCase;

    @GetMapping("/health_check")
    public String health_check(){
        return "It's working on spot-service!";
    }


    @PostMapping("/findNearby")
    public String findNearbyElement(FindNearbyElementRequest request) {
        FindNearbyElementCommand command;
        return "It's working on spot-service!";
    }


}
