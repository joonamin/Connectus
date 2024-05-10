package social.connectus.userservice.domain.application.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyWalkRequest {
	Long userId; // 해당 값은 api gateway를 통해서 전달되는 값으로, validation이 완료된 정보여야한다.
}
