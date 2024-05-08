package social.connectus.userservice.domain.model.entity;

import java.util.function.Function;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Statistics {
	@Id
	private Long id;
	@OneToOne
	@JoinColumn(name = "user_id")
	@MapsId
	private User user;
	private Integer walkCount;
	private Integer postCount;

	@Getter
	@AllArgsConstructor
	public enum Field {
		WALK_COUNT(Statistics::getWalkCount),
		POST_COUNT(Statistics::getPostCount);
		private Function<Statistics, Integer> getter;
	}
}
