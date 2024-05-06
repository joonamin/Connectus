package social.connectus.gatherservice.domain.ports.outbound;

import social.connectus.gatherservice.application.rest.response.CreateGatherResponse;
import social.connectus.gatherservice.common.exception.ResourceNotFoundException;
import social.connectus.gatherservice.domain.command.CloseGatherCommand;
import social.connectus.gatherservice.domain.command.CreateGatherCommand;
import social.connectus.gatherservice.domain.command.JoinGatherCommand;
import social.connectus.gatherservice.domain.command.WantJoinGatherCommand;
import social.connectus.gatherservice.domain.model.Gather;

public interface GatherPort {
    void closeGather(CloseGatherCommand command) throws ResourceNotFoundException;
    CreateGatherResponse createGather(CreateGatherCommand  command);
    Gather getGatherById(long gatherId) throws ResourceNotFoundException;
    void wantJoinGather(WantJoinGatherCommand command) throws ResourceNotFoundException;

    void joinGather(JoinGatherCommand command) throws ResourceNotFoundException;
}
