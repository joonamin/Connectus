package social.connectus.walk.application.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/walk")
public class WalkController {
    @GetMapping("/health_check")
    public String health_check(){
        return "It's walking on Walk-Service!";
    }
}
