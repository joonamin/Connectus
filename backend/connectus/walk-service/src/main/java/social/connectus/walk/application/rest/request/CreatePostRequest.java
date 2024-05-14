package social.connectus.walk.application.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.domain.model.VO.PostVO;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePostRequest {
    private long walkId;
    List<PostVO> postList;
}
