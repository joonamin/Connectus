package social.connectus.location.domain.ports.outbound;

import io.milvus.v2.service.vector.response.InsertResp;
import io.milvus.v2.service.vector.response.QueryResp;
import org.springframework.stereotype.Component;
import social.connectus.location.application.rest.request.SpotDto;
import social.connectus.location.domain.command.CreateSpotCommand;
import social.connectus.location.domain.command.GetSpotCommand;

import java.util.List;

@Component
public interface MilvusPort {
    // TODO: List 형태의 입력과 반환 및 객체화
    InsertResp insert(double longitude, double latitude, Long spotId, Long type, Long domainId);

    List<Long> insertAll(CreateSpotCommand command);

    QueryResp select(double longitude, double latitude);

    List<SpotDto> getSpotList(GetSpotCommand command);
}
