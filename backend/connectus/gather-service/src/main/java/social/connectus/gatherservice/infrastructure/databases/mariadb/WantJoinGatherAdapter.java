package social.connectus.gatherservice.infrastructure.databases.mariadb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import social.connectus.gatherservice.common.constants.GatherConstants;
import social.connectus.gatherservice.common.exception.ResourceNotFoundException;
import social.connectus.gatherservice.domain.command.WantJoinGatherCommand;
import social.connectus.gatherservice.domain.model.Candidate;
import social.connectus.gatherservice.domain.model.Gather;
import social.connectus.gatherservice.domain.ports.outbound.WantJoinGatherPort;

@Component
public class WantJoinGatherAdapter implements WantJoinGatherPort {
//    @Autowired
//    private CandidateRepository candidateRepository;
    @Autowired
    private GatherRepository gatherRepository;

    @Override
    public void wantJoinGather(WantJoinGatherCommand command) throws ResourceNotFoundException {
        long gatherId = command.getGatherId();
        long userId = command.getUserId();
        Gather gather = gatherRepository.findById(gatherId)
                .orElseThrow(()-> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));
        gather.getCandidateList().add(userId);
        gatherRepository.save(gather);
    }
}
