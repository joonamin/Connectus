package social.connectus.gatherservice.application.rest.request;

import lombok.Getter;

@Getter
public class CloseGatherRequest {
    private long gatherId;
    private long userId;
}
