package social.connectus.location.domain.ports.outbound;

import io.milvus.v2.service.vector.response.InsertResp;
import io.milvus.v2.service.vector.response.QueryResp;
import org.springframework.stereotype.Component;

@Component
public interface MilvusPort {
    // TODO: List 형태의 입력과 반환 및 객체화
    InsertResp insert(double longitude, double latitude, Long spotId, Long type, Long domainId);
    QueryResp select(double longitude, double latitude);
}
