package social.connectus.domain.model.RDBMS;

import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;
import social.connectus.application.rest.request.CreatePostRequest;
import social.connectus.application.rest.request.PostRequestDto;
import social.connectus.common.utils.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
	@OneToMany(targetEntity = Comment.class, mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Comment> commentList;
	public static Post from (PostRequestDto dto) {
		return Post.builder()
			.authorId(dto.getAuthorId())
			.imageUrl(dto.getImageUrl())
			.content(dto.getContent())
			.build();
	}
}
