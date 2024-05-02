package social.connectus.gatherservice.infrastructure.databases.mariadb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import social.connectus.gatherservice.application.rest.request.CloseGatherRequest;
import social.connectus.gatherservice.domain.model.Gather;
import social.connectus.gatherservice.domain.ports.outbound.CloseGatherPort;

@Component
public class CloseGatherAdapter implements CloseGatherPort {
    @Autowired
    private GatherRepository gatherRepository;

    @Override
    public void closeGather(CloseGatherRequest request) {
        Gather beforeUpdate = gatherRepository.findById(request.getGatherId())
                .orElseThrow();
        // gather의 closed를 true로 변경
        Gather gather = Gather.builder()
                .id(request.getGatherId())
                .hostId(request.getUserId())
                .isClosed(true)
                .content(beforeUpdate.getContent())
                .endTime(beforeUpdate.getEndTime())
                .maxJoiner(beforeUpdate.getMaxJoiner())
                .candidateList(beforeUpdate.getCandidateList())
                .joinerList(beforeUpdate.getJoinerList())
                .build();

        gatherRepository.save(gather);
    }
}
