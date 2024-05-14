package social.connectus.location.infrastructure.milvus;

import com.alibaba.fastjson.JSONObject;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.service.vector.request.InsertReq;
import io.milvus.v2.service.vector.request.QueryReq;
import io.milvus.v2.service.vector.response.InsertResp;
import io.milvus.v2.service.vector.response.QueryResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import social.connectus.location.common.config.MilvusConfig;
import social.connectus.location.domain.ports.outbound.MilvusPort;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Component
public class MilvusAdapter implements MilvusPort {

    private final MilvusConfig milvusConfig;

    @Override
    public InsertResp insert(double longitude, double latitude, Long spotId, Long type, Long domainId) {
        // client 값 호출
        MilvusClientV2 client = milvusConfig.createMilvusClient();

        // 데이터 생성
        Random ran = new Random();
        JSONObject pingInfo = new JSONObject();
        List<Float> vectorList = new ArrayList<>();
        vectorList.add((float) longitude);
        vectorList.add((float) latitude);

        pingInfo.put("spot", vectorList);
        pingInfo.put("type", type);
        pingInfo.put("domain_id", domainId);
        pingInfo.put("latitude", latitude);
        pingInfo.put("longitude", longitude);
        pingInfo.put("created_at", LocalDateTime.now().toString());
        pingInfo.put("updated_at", LocalDateTime.now().toString());

//        System.out.println("생성 데이터");
//        System.out.println(pingInfo.toString());

        // 삽입 요청
        InsertReq insertReq = InsertReq.builder()
                .collectionName(milvusConfig.getCollectionName())
                .data(Collections.singletonList(pingInfo))
                .build();
        InsertResp insertResp = client.insert(insertReq);
        // TODO: 예외처리 必
        return null;
    }

    @Override
    public QueryResp select(double longitude, double latitude) {

        MilvusClientV2 client = milvusConfig.createMilvusClient();
        // 조회 요청
        String filter = "spot_id != " + -1;

        // 조회 요청 생성
        QueryReq queryReq = QueryReq.builder()
                .collectionName(milvusConfig.getCollectionName())
                .filter(filter)
                .outputFields(Arrays.asList("spot_id", "spot", "latitude", "longitude", "type", "domain_id", "created_at", "updated_at"))
                .limit(10)
                .build();

        return client.query(queryReq);
    }
}
