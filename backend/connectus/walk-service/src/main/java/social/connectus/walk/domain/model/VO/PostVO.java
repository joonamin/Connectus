package social.connectus.walk.domain.model.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostVO {
    private String content;
    private long walkId;
    private long authorId;
    private MultipartFile image;
}
