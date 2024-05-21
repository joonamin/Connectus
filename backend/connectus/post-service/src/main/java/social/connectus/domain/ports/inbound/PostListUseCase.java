package social.connectus.domain.ports.inbound;

import java.util.List;

import social.connectus.application.rest.response.MyPagePostResponse;
import social.connectus.common.annotation.UseCase;

public interface PostListUseCase {
	List<MyPagePostResponse> getMyPagePostList(Long userId);
	List<MyPagePostResponse> getMyLikePostList(List<Long> postListId);
}
