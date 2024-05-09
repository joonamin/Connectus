package social.connectus.location.domain.services;

import io.milvus.v2.service.vector.response.QueryResp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import social.connectus.location.application.rest.response.FindNearbyElementResponse;
import social.connectus.location.common.type.Ping;
import social.connectus.location.common.type.PingType;
import social.connectus.location.domain.command.FindNearbyElementCommand;
import social.connectus.location.domain.ports.inbound.SpotUseCase;
import social.connectus.location.domain.ports.outbound.MilvusPort;
import social.connectus.location.domain.ports.outbound.SpotPort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Transactional
@Component
public class SpotService implements SpotUseCase {

    private final MilvusPort milvusPort;

    @Override
    public FindNearbyElementResponse findNearbyElement(FindNearbyElementCommand command) {
        QueryResp resp = milvusPort.select(command.getLongitude(), command.getLatitude());
        List<QueryResp.QueryResult> results = resp.getQueryResults();

        ArrayList<Ping> nearby = new ArrayList<>();
        for (QueryResp.QueryResult queryResult : results) {
            Map<String, Object> row = queryResult.getEntity();
            /*
             * {
             *  domain_id=4305556974050930190,
             *  updated_at=2024-05-09T11:34:22.928575400,
             *  spot_id=449616917989181707,
             *  spot=[0.15342987, 0.8820968],
             *  created_at=2024-05-09T11:34:22.928575400,
             *  type=2854591712224326841}
             * */
            ArrayList<Double> coordinate = (ArrayList<Double>) row.get("spot");
            Ping ping = Ping.builder()
                    .id((Long) row.get("spot_id"))
                    .domainId((Long) row.get("domain_id"))
                    .longitude(coordinate.get(0))
                    .latitude(coordinate.get(1))
                    .type(PingType.valueOf((String) row.get("type")))
                    .cretedAt((LocalDateTime) row.get("created_at"))
                    .updatedAt((LocalDateTime) row.get("updated_at"))
                    .build();
            nearby.add(ping);
//            System.out.println("Row " + i + ": " + row);
        }
        return FindNearbyElementResponse.builder()
                .nearby(nearby)
                .build();
    }
}
