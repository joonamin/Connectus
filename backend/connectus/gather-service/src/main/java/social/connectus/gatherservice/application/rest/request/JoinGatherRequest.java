package social.connectus.gatherservice.application.rest.request;

import lombok.Getter;

@Getter
public class JoinGatherRequest {
    private long gatherId;
    private long userId;
}
