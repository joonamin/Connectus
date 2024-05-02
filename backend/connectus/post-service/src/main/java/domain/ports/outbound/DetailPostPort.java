package domain.ports.outbound;

import application.rest.response.DetailPostResponse;
import common.exception.BusinessException;
import domain.model.Post;
import infrastructure.databases.mariadb.repository.PostRepository;

public interface DetailPostPort {
	DetailPostResponse detailPost(Long postId) throws BusinessException;
}
