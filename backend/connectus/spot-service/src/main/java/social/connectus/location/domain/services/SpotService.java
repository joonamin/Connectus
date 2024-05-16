package social.connectus.location.domain.services;

import io.milvus.v2.service.vector.response.QueryResp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import social.connectus.location.application.rest.response.FindNearbyElementResponse;
import social.connectus.location.common.type.DomainType;
import social.connectus.location.common.type.Ping;
import social.connectus.location.common.type.PingType;
import social.connectus.location.domain.command.CreateSpotCommand;
import social.connectus.location.domain.command.FindNearbyElementCommand;
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
        QueryResp resp = milvusPort.select(command.getLongitude(), command.getLatitude());
        System.out.println(resp);

        List<QueryResp.QueryResult> results = resp.getQueryResults();

        ArrayList<Ping> nearby = new ArrayList<>();
        for (QueryResp.QueryResult queryResult : results) {
            Map<String, Object> row = queryResult.getEntity();

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
    public void updateDomain(Long spotId, DomainType domainType, Long domainId) {
        // todo: 이거 업데이트 해주세요
    }
}
