package com.social.eventservice.domain.dto;

import com.social.eventservice.domain.model.Event;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {
    private Long eventId;
    private Integer reward;
    private Boolean isFinished;
    private String imageUrl;
    private Long hostId;
    private String title;
    private String description;
    private List<Long> spotIdList;
    private List<Long> onClearUserIdList;

    public static EventDto from(Event event){
        return EventDto.builder()
                .eventId(event.getId())
                .reward(event.getReward())
                .isFinished(event.getIsFinished())
                .imageUrl(event.getImageUrl())
                .hostId(event.getHostId())
                .title(event.getTitle())
                .description(event.getDescription())
                .spotIdList(event.getSpotIdList())
                .onClearUserIdList(event.getOnClearUserIdList())
                .build();
    }
}
