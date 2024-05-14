package social.connectus.userservice.domain.application.response;

import java.io.Serializable;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpenedPostResponse implements Serializable {
	private List<Long> openedPostList;
}
