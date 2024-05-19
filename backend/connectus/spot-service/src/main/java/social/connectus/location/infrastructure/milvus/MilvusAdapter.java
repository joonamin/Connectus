package social.connectus.location.infrastructure.milvus;

import com.alibaba.fastjson.JSONObject;
import io.milvus.client.MilvusClient;
import io.milvus.grpc.MutationResult;
import io.milvus.grpc.QueryResults;
import io.milvus.param.R;
import io.milvus.param.dml.DeleteParam;
import io.milvus.param.dml.InsertParam;
import io.milvus.param.dml.QueryParam;
import io.milvus.param.highlevel.dml.DeleteIdsParam;
import io.milvus.param.highlevel.dml.GetIdsParam;
import io.milvus.param.highlevel.dml.response.DeleteResponse;
import io.milvus.param.highlevel.dml.response.GetResponse;
import io.milvus.response.QueryResultsWrapper;
import io.milvus.v2.service.vector.request.DeleteReq;
import io.milvus.v2.service.vector.request.InsertReq;
import io.milvus.v2.service.vector.response.InsertResp;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NullArgumentException;
import org.springframework.stereotype.Component;
import social.connectus.location.application.rest.request.SpotDto;
import social.connectus.location.common.config.MilvusConfig;
import social.connectus.location.common.type.PingType;
import social.connectus.location.domain.command.CreateSpotCommand;
import social.connectus.location.domain.command.DeleteSpotCommand;
import social.connectus.location.domain.command.GetSpotCommand;
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
        MilvusClient client = milvusConfig.createMilvusClient();

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
//        InsertResp insertResp = client.insert(insertReq);
        // TODO: 예외처리 必
        return null;
    }

    @Override
    public List<Long> insertAll(CreateSpotCommand command){
        // client 값 호출
        MilvusClient client = milvusConfig.createMilvusClient();

        // 데이터 생성
        List<JSONObject> insertData = new ArrayList<>();
        for(SpotDto spotDto: command.getSpotList()){
            JSONObject spot = new JSONObject();

            // 2차원 float을 요구하는 spot
            List<List<Double>> latlng2d = new ArrayList<>();
            List<Float> latlng = new ArrayList<>();
            latlng.add(spotDto.getLatitude().floatValue());
            latlng.add(spotDto.getLongitude().floatValue());
//            latlng2d.add(latlng);

            if(spotDto.getLatitude() == null
            || spotDto.getLongitude() == null
            || spotDto.getType() == null
            || spotDto.getDomainId() == null){
                throw new RuntimeException("Null parameter doesn't expected.");
            }

            spot.put("spot", latlng);
            spot.put("type", spotDto.getType().toString());
            spot.put("domain_id", spotDto.getDomainId());
            spot.put("latitude", spotDto.getLatitude().floatValue());
            spot.put("longitude", spotDto.getLongitude().floatValue());
            spot.put("created_at", LocalDateTime.now().toString());
            spot.put("updated_at", LocalDateTime.now().toString());
            insertData.add(spot);
        }
        // 삽입 요청
        InsertParam insertReq = InsertParam .newBuilder()
                .withCollectionName(milvusConfig.getCollectionName())
                .withRows(insertData)
                .build();
        R<MutationResult> mutationResp = client.insert(insertReq);
        MutationResult mutationResult = mutationResp.getData();
        if (mutationResp.getStatus() != R.Status.Success.getCode()) {
            System.out.println(mutationResp.getMessage());
        }

        List<Long> result = mutationResult.getIDs().getIntId().getDataList().stream().toList();

        return result;
    }

    @Override
    public R<QueryResults> select(double longitude, double latitude) {

        MilvusClient client = milvusConfig.createMilvusClient();
        // 조회 요청
        String filter = "spot_id != " + -1;

//        List<List<Float>> searchsearch = new ArrayList<>();
//        List<Float> searchData = new ArrayList<>();
//        searchData.add((float)longitude + 1.0f);
//        searchData.add((float)latitude + 1.5f);
//        searchsearch.add(searchData);
//
//        Map<String,Object> searchParams = new HashMap<>();
//        searchParams.put("metric_type ", "L2");
//        searchParams.put("radius", 3.0f);
//        searchParams.put("range_filter ", 5.0f);
//
//
//        SearchParam request = SearchParam.newBuilder()
//                .withCollectionName("spot")
//                .withMetricType(MetricType.L2)
//                .withParams("{\"radius\":3.0,\"range_filter\":1.0}")
//                .withFloatVectors(searchsearch)
//                .withVectorFieldName("spot")
//                .withTopK(16384)
//                .build();
//        R<SearchResults> resp = client.search(request);
//        return resp;
//        System.out.println(resp.getMessage());

//         조회 요청 생성
        QueryParam queryReq = QueryParam.newBuilder()
                .withCollectionName(milvusConfig.getCollectionName())
                .withExpr(filter)
                .withOutFields(Arrays.asList("spot_id", "spot", "latitude", "longitude", "type", "domain_id", "created_at", "updated_at"))
                .build();
        return client.query(queryReq);
    }

    @Override
    public List<SpotDto> getSpotList(GetSpotCommand command) {

        MilvusClient  client = milvusConfig.createMilvusClient();
        // 조회 요청
        String filter = "spot_id != " + -1;

        // 조회 요청 생성
//        QueryReq queryReq = QueryReq.builder()
//                .collectionName(milvusConfig.getCollectionName())
//                .filter(filter)
//                .outputFields(Arrays.asList("spot_id", "spot", "latitude", "longitude", "type", "domain_id", "created_at", "updated_at"))
//                .limit(20)
//                .build();
//        QueryResp result = client.query(queryReq);

        GetIdsParam param = GetIdsParam.newBuilder()
                .withCollectionName(milvusConfig.getCollectionName())
                .withPrimaryIds(command.getSpotIdList())
                .withOutputFields(Arrays.asList("spot_id", "spot", "latitude", "longitude", "type", "domain_id", "created_at", "updated_at"))
                .build();

        R<GetResponse> result = client.get(param);
//        List<QueryResp.QueryResult> queryResultList = null;
//        List<QueryResp.QueryResult> queryResultList = result.getQueryResults();
        List<SpotDto> response = new ArrayList<>();
        for(QueryResultsWrapper.RowRecord rowRecord : result.getData().getRowRecords()){
            Long spotId = (Long) rowRecord.get("spot_id");
//            List<Float> spot = (List<Float>) rowRecord.get("spot");
            Float latitude = (Float) rowRecord.get("latitude");
            Float longitude = (Float) rowRecord.get("longitude");
            String type = (String) rowRecord.get("type");
            Long domainId = (Long) rowRecord.get("domain_id");
            String createdAt = (String) rowRecord.get("created_at");
            String updatedAt = (String) rowRecord.get("updated_at");

            SpotDto spotDto = SpotDto.builder()
                    .spotId(spotId)
                    .domainId(domainId)
                    .latitude(latitude)
                    .longitude(longitude)
                    .type(PingType.valueOf(type))
                    .build();
            response.add(spotDto);
        }

        return response;
    }


    @Override
    public List<Long> deleteSpotList(DeleteSpotCommand command) {
        // client 값 호출
        MilvusClient client = milvusConfig.createMilvusClient();

        // 기존 데이터 삭제
        List<Long> spotIdListDto = command.getSpotIdList();
//        if(spotIdListDto.stream().anyMatch(Objects::isNull)){
//            throw new RuntimeException("Null parameter doesn't expected.");
//        }

        List<Long> ids = spotIdListDto.stream().filter(id -> id!=null).toList();
//        List<Long> ids = spotIdListDto;

        DeleteIdsParam param = DeleteIdsParam.newBuilder()
                .withCollectionName(milvusConfig.getCollectionName())
                .withPrimaryIds(ids)
                .build();
        R<DeleteResponse> response = client.delete(param);
        if (response.getStatus() != R.Status.Success.getCode()) {
            throw new RuntimeException("Cannot delete old spot!");
        }

        return (List<Long>) response.getData().getDeleteIds();
    }

    @Override
    public List<Long> updateSpotList(CreateSpotCommand command) {
        // 기존 데이터 삭제
        deleteSpotList(DeleteSpotCommand.builder()
                .spotIdList(command.getSpotList().stream().map(SpotDto::getSpotId).toList())
                .build());
        // 신규 데이터 입력
        return insertAll(command);
    }

    @Override
    public Boolean deleteAll() {
        MilvusClient client = milvusConfig.createMilvusClient();
        R<MutationResult> response = client.delete(DeleteParam.newBuilder()
                .withCollectionName("spot")
                .withExpr("type == \"USER\"")
                .build());

        if (response.getStatus() != R.Status.Success.getCode()) {
            return false;
//            throw new RuntimeException("Cannot delete old spot!");
        }
        return true;
    }
}
