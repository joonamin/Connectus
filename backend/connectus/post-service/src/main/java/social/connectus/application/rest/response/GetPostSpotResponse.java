package social.connectus.application.rest.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPostSpotResponse {
    private Long postId;
    private Double latitude;
    private Double longitude;
}
