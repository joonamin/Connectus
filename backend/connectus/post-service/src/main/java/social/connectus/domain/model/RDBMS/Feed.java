package social.connectus.domain.model.RDBMS;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import social.connectus.application.rest.request.CreateFeedRequestDto;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feed {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long walkId;
	private String postIdList;
	public static Feed from(CreateFeedRequestDto dto, List<Long> postIdList) {
		return Feed.builder()
			.walkId(dto.getWalkId())
			.postIdList(postIdList.toString())
			.build();
	}
}
