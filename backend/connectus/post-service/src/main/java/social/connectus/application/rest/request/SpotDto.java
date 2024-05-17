package social.connectus.application.rest.request;

import lombok.*;
import social.connectus.domain.model.RDBMS.Post;
import social.connectus.domain.service.command.PostSpotCommand;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpotDto {
    private Long spotId;
    private Long domainId;
    private Double longitude;
    private Double latitude;
    private String type;

    public static SpotDto from(Post post, PostRequestDto postRequestDto) {
        return SpotDto.builder()
                .domainId(post.getId())
                .longitude(postRequestDto.getLongitude())
                .latitude(postRequestDto.getLatitude())
                .type("POST")
                .build();
    }
}
