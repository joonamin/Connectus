package social.connectus.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import social.connectus.common.type.Type;

@Entity
@Getter
@AllArgsConstructor
@Builder
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;
	private Long domainId;
	private Type type;

	public static Likes of(Long userId, Long domainId, Type type) {
		return Likes.builder()
			.domainId(domainId)
			.userId(userId)
			.type(type)
			.build();
	}
}
