package social.connectus.userservice.application.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointResponse {
    private Long userId;
    private Integer currentPoint;
}
