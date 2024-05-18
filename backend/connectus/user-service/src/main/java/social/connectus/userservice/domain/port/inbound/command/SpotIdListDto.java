package social.connectus.userservice.domain.port.inbound.command;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpotIdListDto {
    private List<Long> spotIdList;
}
