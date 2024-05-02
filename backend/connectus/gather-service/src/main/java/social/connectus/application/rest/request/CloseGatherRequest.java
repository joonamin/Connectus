package social.connectus.application.rest.request;

import lombok.Getter;

@Getter
public class CloseGatherRequest {
    private long gatherId;
    private long userId;
}
