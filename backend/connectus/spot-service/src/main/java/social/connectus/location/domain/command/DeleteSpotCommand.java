package social.connectus.location.domain.command;

import lombok.*;
import social.connectus.location.application.rest.request.DeleteSpotRequest;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteSpotCommand {
    private List<Long> spotIdList;

    public static DeleteSpotCommand from(DeleteSpotRequest request) {
        return DeleteSpotCommand.builder()
                .spotIdList(request.getSpotIdList())
                .build();
    }
}
