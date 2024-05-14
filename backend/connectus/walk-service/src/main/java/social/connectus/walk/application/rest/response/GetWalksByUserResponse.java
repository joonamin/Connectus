package social.connectus.walk.application.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.domain.model.entity.Walk;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetWalksByUserResponse {
    private List<GetWalkResponse> walks;

    public static GetWalksByUserResponse from(List<Walk> walkList){
        List<GetWalkResponse> walks = walkList.stream()
                .map(GetWalkResponse::from)
                .toList();
        return GetWalksByUserResponse.builder()
                .walks(walks)
                .build();
    }
}
