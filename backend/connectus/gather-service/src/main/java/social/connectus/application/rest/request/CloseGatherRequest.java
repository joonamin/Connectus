package social.connectus.application.rest.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CloseGatherRequest {
    private long gather_id;
    private long user_id;
}
