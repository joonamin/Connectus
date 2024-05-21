package social.connectus.userservice.domain.command;

import lombok.*;
import social.connectus.userservice.application.request.PointChangeRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointChangeCommand {
    private Long userId;
    private Integer changeValue;

    public static PointChangeCommand from(PointChangeRequest request){
        return PointChangeCommand.builder()
                .userId(request.getUserId())
                .changeValue(request.getChangeValue())
                .build();
    }
}
