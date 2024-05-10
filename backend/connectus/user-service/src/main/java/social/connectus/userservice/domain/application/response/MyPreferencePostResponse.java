package social.connectus.userservice.domain.application.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import social.connectus.userservice.domain.port.client.response.PostRecord;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyPreferencePostResponse {
	private List<PostRecord> preferenceRecords;
}
