package social.connectus.domain.ports.outbound;

import java.util.List;

import social.connectus.application.rest.response.MyPagePostResponse;

public interface PostListPort {
	List<MyPagePostResponse> getMyPagePostList(Long userId);
	List<MyPagePostResponse> getMyLikePostList(List<Long> postIdList);
}
