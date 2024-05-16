package social.connectus.location.application.rest.controller;

import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.service.collection.request.DescribeCollectionReq;
import io.milvus.v2.service.collection.request.GetCollectionStatsReq;
import io.milvus.v2.service.collection.response.DescribeCollectionResp;
import io.milvus.v2.service.collection.response.GetCollectionStatsResp;
import io.milvus.v2.service.vector.request.DeleteReq;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.connectus.location.application.rest.request.CreateSpotRequest;
import social.connectus.location.application.rest.request.FindNearbyElementRequest;
import social.connectus.location.application.rest.request.GetSpotRequest;
import social.connectus.location.application.rest.request.SpotDto;
import social.connectus.location.application.rest.response.FindNearbyElementResponse;
import social.connectus.location.application.rest.response.GetSpotResponse;
import social.connectus.location.common.config.MilvusConfig;
import social.connectus.location.domain.command.CreateSpotCommand;
import social.connectus.location.domain.command.FindNearbyElementCommand;
import social.connectus.location.domain.command.GetSpotCommand;
import social.connectus.location.domain.ports.inbound.SpotUseCase;
import social.connectus.location.domain.ports.outbound.MilvusPort;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/spot")
@AllArgsConstructor
public class SpotController {

    private final SpotUseCase spotUseCase;
    private final MilvusConfig milvusConfig;        // 테스트를 위함. 삭제 요망.

    @GetMapping("/health-check")
    public String health_check(){
        return "It's working on spot-service!";
    }


    @PostMapping("/findNearby")
    public FindNearbyElementResponse findNearbyElement(@RequestBody FindNearbyElementRequest request) {
        FindNearbyElementCommand command = new FindNearbyElementCommand(request.getLongitude(), request.getLongitude());
        return spotUseCase.findNearbyElement(command);
    }

    @PostMapping("/insert")
    public List<Long> createSpot(@RequestBody CreateSpotRequest request){
        CreateSpotCommand command = CreateSpotCommand.from(request);
        return spotUseCase.createSpot(command);
    }

    @PostMapping("/get")
    public ResponseEntity<GetSpotResponse> getSpotList(@RequestBody GetSpotRequest request){
        GetSpotResponse response = GetSpotResponse.builder()
                .spotList(spotUseCase.getSpot(GetSpotCommand.from(request)))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/describe-collection")
    public DescribeCollectionResp describeCollection(){
        // client 값 호출
        MilvusClientV2 client = milvusConfig.createMilvusClient();
        return client.describeCollection(DescribeCollectionReq.builder()
                .collectionName("spot")
                .build());
    }

    @GetMapping("/delete")
    public ResponseEntity<Void> deleteAll(){
        // client 값 호출
        MilvusClientV2 client = milvusConfig.createMilvusClient();
        client.delete(DeleteReq.builder()
                .collectionName("spot")
                .filter("spot_id != -1")
                .build());
        return ResponseEntity.ok().build();
    }
}
