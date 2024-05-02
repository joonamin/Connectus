package domain.model;

import java.util.List;

import application.rest.request.PostRequestDto;
import common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long authorId;
	private Long walkId;
	private String imageUrl;
	private String content;
	private List<Comment> commentList;

	public static Post from (PostRequestDto dto) {
		return Post.builder()
			.authorId(dto.getAuthorId())
			// .imageUrl()  // TODO : image 지정하는 방법 찾아봅시다
			.content(dto.getContent())
			.walkId(dto.getWalkId())
			.build();
	}
}
