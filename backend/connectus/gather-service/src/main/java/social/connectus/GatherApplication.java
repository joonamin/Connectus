package social.connectus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatherApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatherApplication.class, args);
    }
}
