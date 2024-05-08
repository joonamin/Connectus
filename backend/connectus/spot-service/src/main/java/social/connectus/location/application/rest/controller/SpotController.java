package social.connectus.location.application.rest.controller;

import com.alibaba.fastjson.JSONObject;
import io.milvus.client.MilvusServiceClient;
import io.milvus.param.ConnectParam;
import io.milvus.param.dml.InsertParam;
import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.service.collection.request.LoadCollectionReq;
import io.milvus.v2.service.vector.request.InsertReq;
import io.milvus.v2.service.vector.request.QueryReq;
import io.milvus.v2.service.vector.response.QueryResp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import social.connectus.location.application.rest.request.FindNearbyElementRequest;
import social.connectus.location.domain.command.FindNearbyElementCommand;
import social.connectus.location.domain.ports.inbound.SpotUseCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
@RestController
@RequestMapping("/spot")
@AllArgsConstructor
public class SpotController {

    @Autowired
    private final SpotUseCase gatherUseCase;

    @GetMapping("/health_check")
    public String health_check(){
        // Milvus 연결 설정
        ConnectConfig connectConfig = ConnectConfig.builder()
                .uri("https://milvus.connectus.social:19530")
                .build();

        MilvusClientV2 client = new MilvusClientV2(connectConfig);


        // 컬렉션 로드
        String collectionName = "spot";

        // 컬렉션 로드
        System.out.println("컬렉션 로드");
        LoadCollectionReq loadReq = LoadCollectionReq.builder()
                .collectionName(collectionName)
                .build();

        client.loadCollection(loadReq);

        // 데이터 생성
        Random ran = new Random();
        JSONObject pingInfo = new JSONObject();

        // 고유 ID
        Long spot_id = Math.abs(ran.nextLong());
        // 벡터 값 (경도, 위도)은 List<Float> 형태로 담아야함
        Float longitude = ran.nextFloat(), latitude = ran.nextFloat();
        List<Float> vectorList = new ArrayList<>();
        vectorList.add(longitude);
        vectorList.add(latitude);
        // 그 외의 값 ping type, domain ID 생성
        Long type = Math.abs(ran.nextLong()), domain_id = Math.abs(ran.nextLong());

        // JSONObject 형태만 받기에 key/value 형태로 담아줌
        // Mulivus Key는 Field 값과 일치 하도록 작성
        pingInfo.put("spot_id", spot_id);
        pingInfo.put("spot", vectorList);
        pingInfo.put("type", type);
        pingInfo.put("domain_id", domain_id);

        System.out.println("생성 데이터");
        System.out.println(pingInfo.toString());

        // 삽입 요청
        InsertReq insertReq = InsertReq.builder()
                .collectionName(collectionName)
                .data(Collections.singletonList(pingInfo))
                .build();
        client.insert(insertReq);

        System.out.println("삽입 완료!");

        // 조회 요청
        String filter = "spot_id != " + -1;

        // 조회 요청 생성
        QueryReq queryReq = QueryReq.builder()
                .collectionName(collectionName)
                .filter(filter)
//                .outputFields(Arrays.asList("spot_id", "spot", "type", "domain_id")) // 조회할 필드 목록
                .limit(10) // 원하는 결과의 최대 개수
                .build();

        // 쿼리 수행
        QueryResp results = client.query(queryReq);

        System.out.println("조회 결과:");
        for (int i = 0; i < results.getQueryResults().size(); i++) {
            QueryResp.QueryResult queryResult = results.getQueryResults().get(i);
            Map<String, Object> row = queryResult.getEntity();
            System.out.println("Row " + i + ": " + row);
        }

        return "It's working on spot-service!";
    }


    @PostMapping("/findNearby")
    public String findNearbyElement(FindNearbyElementRequest request) {
        FindNearbyElementCommand command;
        return "It's working on spot-service!";
    }


}
