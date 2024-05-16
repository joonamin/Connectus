package social.connectus.domain.model.mongoDB;

import org.springframework.data.redis.core.RedisHash;

import social.connectus.application.rest.request.CoordinateRequestDto;
import social.connectus.application.rest.request.CreatePostRequest;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.application.rest.request.SpotDto;

@Getter
@Builder
@RedisHash(value = "post", timeToLive = 60*60)
@AllArgsConstructor
@NoArgsConstructor
public class PostRedis {
	@Id
	private String id;
	private String content;
	private String imageUrl;
	private Long authorId;
	private Long walkId;
	private double longitude;
	private double latitude;

	public static PostRedis from(CreatePostRequest request, SpotDto coordinateRequestDto) {
		return PostRedis.builder()
			.authorId(request.getAuthorId())
			.imageUrl(request.getImageUrl())
			.content(request.getContent())
			.walkId(request.getWalkId())
			.longitude(coordinateRequestDto.getLongitude())
			.latitude(coordinateRequestDto.getLatitude())
			.build();
	}
}
