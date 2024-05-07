package social.connectus.walk.infrastructure.external;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.cloud.openfeign.FeignClient(name = "gather-service")
public interface FeignClient {
    @GetMapping("/gather/health_check")
    String healthCheck();
}
