package social.connectus.location.application.rest.controller;

import com.alibaba.fastjson.JSONObject;
import io.milvus.client.MilvusServiceClient;
import io.milvus.param.ConnectParam;
import io.milvus.param.dml.InsertParam;
import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.service.vector.request.InsertReq;
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
        return "It's working on spot-service!";
    }
        ConnectConfig connectConfig = ConnectConfig.builder()
                .uri("https://milvus.connectus.social:19530")
                .build();

        MilvusClientV2 client = new MilvusClientV2(connectConfig);

        Random ran = new Random();
        List<Long> book_id_array = new ArrayList<>();
        List<Long> word_count_array = new ArrayList<>();
        List<List<Float>> book_intro_array = new ArrayList<>();
        for (long i = 0L; i < 10; ++i) {
            book_id_array.add(i);
            word_count_array.add(i + 10000);
            List<Float> vector = new ArrayList<>();
            for (int k = 0; k < 2; ++k) {
                vector.add(ran.nextFloat());
            }
            book_intro_array.add(vector);
        }

        List<InsertParam.Field> fields = new ArrayList<>();
        fields.add(new InsertParam.Field("book_id", book_id_array));
        fields.add(new InsertParam.Field("word_count", word_count_array));
        fields.add(new InsertParam.Field("book_intro", book_intro_array));

        JSONObject vector = new JSONObject();
        vector.put("book_intro", book_intro_array.get(0));

        InsertReq insertReq = InsertReq.builder()
                .collectionName("book")
                .data(Collections.singletonList(vector))
                .build();
        client.insert(insertReq);

        client.insert(InsertReq.builder()
                .collectionName("book")
                .data(Collections.singletonList(vector))
                .build()
        );

    @PostMapping("/findNearby")
    public String findNearbyElement(FindNearbyElementRequest request) {
        FindNearbyElementCommand command;
        return "It's working on spot-service!";
    }


}
