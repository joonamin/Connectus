package infrastructure.databases.mariadb.adapter;

import org.springframework.stereotype.Component;

import com.querydsl.jpa.impl.JPAQueryFactory;

import application.rest.response.DetailPostResponse;
import common.exception.BusinessException;
import domain.model.Post;
import domain.ports.outbound.DetailPostPort;
import infrastructure.databases.mariadb.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DetailPostAdapter implements DetailPostPort {
	private final PostRepository postRepository;
	@Override
	public DetailPostResponse detailPost(Long postId) throws BusinessException {
		Post post = postRepository.findById(postId).orElseThrow(()->new BusinessException("Post doesn't exist"));
		return DetailPostResponse.from(post);
	}
}
