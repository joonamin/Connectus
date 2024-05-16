package social.connectus.location.domain.services;

import io.milvus.grpc.QueryResults;
import io.milvus.param.R;
import io.milvus.response.QueryResultsWrapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import social.connectus.location.application.rest.request.SpotDto;
import social.connectus.location.application.rest.response.FindNearbyElementResponse;
import social.connectus.location.common.type.Ping;
import social.connectus.location.common.type.PingType;
import social.connectus.location.domain.command.CreateSpotCommand;
import social.connectus.location.domain.command.FindNearbyElementCommand;
import social.connectus.location.domain.command.GetSpotCommand;
import social.connectus.location.domain.ports.inbound.SpotUseCase;
import social.connectus.location.domain.ports.outbound.MilvusPort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Transactional
@Service
public class SpotService implements SpotUseCase {

//    private final SpotPort spotPort;
    private final MilvusPort milvusPort;

    @Override
    public FindNearbyElementResponse findNearbyElement(FindNearbyElementCommand command) {
        R<QueryResults> resp = milvusPort.select(command.getLongitude(), command.getLatitude());
//        System.out.println(resp);

        QueryResultsWrapper wrapper = new QueryResultsWrapper(resp.getData());
        List<QueryResultsWrapper.RowRecord> results = wrapper.getRowRecords();

        ArrayList<Ping> nearby = new ArrayList<>();
        for (QueryResultsWrapper.RowRecord queryResult : results) {
            Map<String, Object> row = queryResult.getFieldValues();

            List<Float> coordinate = (List<Float>) row.get("spot");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");

            Ping ping = Ping.builder()
                    .id((Long) row.get("spot_id"))
                    .domainId((Long) row.get("domain_id"))
                    .longitude(coordinate.get(0))
                    .latitude(coordinate.get(1))
                    .type(PingType.valueOf((String) row.get("type")))
                    .cretedAt(LocalDateTime.parse((String) row.get("created_at"), formatter))
                    .updatedAt(LocalDateTime.parse((String) row.get("updated_at"), formatter))
                    .build();
            nearby.add(ping);
        }
        return FindNearbyElementResponse.builder()
                .nearby(nearby)
                .build();
    }

    @Override
    public List<Long> createSpot(CreateSpotCommand command){
        return milvusPort.insertAll(command);
    }

    @Override
    public List<SpotDto> getSpot(GetSpotCommand command) {
        return milvusPort.getSpotList(command);
    }
}
