package social.connectus.walk.infrastructure.external;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import social.connectus.walk.application.rest.request.CreatePostRequest;
import social.connectus.walk.domain.command.GetAchievementsCommand;
import social.connectus.walk.domain.model.entity.Post;

import java.util.List;

@org.springframework.cloud.openfeign.FeignClient(name = "post-service")
public interface PostClient{
    @PostMapping("/post/insert")
    List<Long> createPost(@RequestBody CreatePostRequest request);
}
