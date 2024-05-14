package social.connectus.walk.application.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetWalkSliceResponse {
    private List<GetWalkResponse> walksList;
    private int pageSize;
    private int pageNum;
    private boolean hasNext;
}
