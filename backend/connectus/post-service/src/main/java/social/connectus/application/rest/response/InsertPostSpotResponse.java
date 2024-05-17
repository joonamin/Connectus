package social.connectus.application.rest.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertPostSpotResponse {
    private List<Long> spotIdList;
}
