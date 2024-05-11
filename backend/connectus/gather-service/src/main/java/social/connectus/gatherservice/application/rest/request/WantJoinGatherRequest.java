package social.connectus.gatherservice.application.rest.request;

import lombok.Getter;

@Getter
public class WantJoinGatherRequest {
    private long gatherId;
    private long userId;
}
