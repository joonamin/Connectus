package social.connectus.gatherservice.application.rest.response;

import lombok.Builder;
import lombok.Getter;
import social.connectus.gatherservice.domain.model.Gather;

@Builder
@Getter
public class CreateGatherResponse {
    private long id;

    public static CreateGatherResponse from(Gather entity){
        return CreateGatherResponse.builder()
                .id(entity.getId())
                .build();
    }
}
