package social.connectus.application.rest.request;

import lombok.Getter;

@Getter
public class WantJoinGatherRequest {
    private long gatherId;
    private long userId;
}
