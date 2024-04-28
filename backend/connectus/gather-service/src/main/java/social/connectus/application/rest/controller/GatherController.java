package social.connectus.application.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gather")
public class GatherController {
    @GetMapping("/health_check")
    public String health_check(){
        return "It's working on gather-service!";
    }
}
