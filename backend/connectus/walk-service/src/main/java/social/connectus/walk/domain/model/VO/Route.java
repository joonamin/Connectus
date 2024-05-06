package social.connectus.walk.domain.model.VO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import social.connectus.walk.domain.model.entity.BaseEntity;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route{
    private List<Long> positionList;
    private int walkTime;
    private int walkDistance;   // 산책 거리
}
