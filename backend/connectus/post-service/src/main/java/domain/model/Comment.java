package domain.model;

import application.rest.request.CreateCommentRequestDto;
import application.rest.response.CreateCommentResponse;
import common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Long postId;
	private Long authorId;
	private String content;

	public static Comment from(CreateCommentRequestDto dto, Long postId) {
		return Comment.builder()
			.postId(postId)
			.authorId(dto.getAuthorId())
			.content(dto.getContent())
			.build();
	}
}
