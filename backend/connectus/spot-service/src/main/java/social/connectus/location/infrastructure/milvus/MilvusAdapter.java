package social.connectus.location.infrastructure.milvus;

import com.alibaba.fastjson.JSONObject;
import io.milvus.client.MilvusServiceClient;
import io.milvus.param.ConnectParam;
import io.milvus.param.dml.InsertParam;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.common.DataType;
import io.milvus.v2.service.vector.request.InsertReq;
import io.milvus.v2.service.vector.request.QueryReq;
import io.milvus.v2.service.vector.response.InsertResp;
import io.milvus.v2.service.vector.response.QueryResp;
import lombok.RequiredArgsConstructor;
import social.connectus.location.common.config.MilvusConfig;
import social.connectus.location.domain.command.FindNearbyElementCommand;
import social.connectus.location.domain.ports.outbound.MlivusPort;

import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
public class MilvusAdapter implements MlivusPort {

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

        pingInfo.put("spot_id", spotId);
        pingInfo.put("spot", vectorList);
        pingInfo.put("type", type);
        pingInfo.put("domain_id", domainId);

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
//                .outputFields(Arrays.asList("spot_id", "spot", "type", "domain_id")) // 조회할 필드 목록
                .limit(10) // 원하는 결과의 최대 개수
                .build();
        // 쿼리 수행
        QueryResp results = client.query(queryReq);

        for (int i = 0; i < results.getQueryResults().size(); i++) {
            QueryResp.QueryResult queryResult = results.getQueryResults().get(i);
            Map<String, Object> row = queryResult.getEntity();
            System.out.println("Row " + i + ": " + row);
        }
        return null;
    }
}
