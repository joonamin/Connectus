package social.connectus.location.domain.ports.outbound;

import io.milvus.v2.service.vector.response.InsertResp;
import io.milvus.v2.service.vector.response.QueryResp;
import org.springframework.stereotype.Component;
import social.connectus.location.domain.command.CreateSpotCommand;

import java.util.List;

@Component
public interface MilvusPort {
    // TODO: List 형태의 입력과 반환 및 객체화
    InsertResp insert(double longitude, double latitude, Long spotId, Long type, Long domainId);

    List<Long> insertAll(CreateSpotCommand command);

    QueryResp select(double longitude, double latitude);

    // TODO: 여기에 id에 따른 update 기능을 하는 인터페이스를 정의해주세요!
}
