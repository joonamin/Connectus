package social.connectus.gatherservice.domain.ports.outbound;


import social.connectus.gatherservice.common.exception.ResourceNotFoundException;
import social.connectus.gatherservice.domain.command.WantJoinGatherCommand;
import social.connectus.gatherservice.domain.model.Candidate;

public interface WantJoinGatherPort {
    void wantJoinGather(WantJoinGatherCommand command) throws ResourceNotFoundException;
}
