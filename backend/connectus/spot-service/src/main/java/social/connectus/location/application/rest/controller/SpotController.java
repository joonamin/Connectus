package social.connectus.location.application.rest.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


}
