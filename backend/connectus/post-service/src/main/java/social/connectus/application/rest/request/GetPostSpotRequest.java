package social.connectus.application.rest.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPostSpotRequest {
    private List<Long> spotIdList;
}
