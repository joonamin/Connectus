package social.connectus.gatherservice.domain.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import social.connectus.gatherservice.application.rest.request.WantJoinGatherRequest;
import social.connectus.gatherservice.common.constants.GatherConstants;
import social.connectus.gatherservice.common.customannotations.UseCase;
import social.connectus.gatherservice.common.exception.ClosedGatherException;
import social.connectus.gatherservice.common.exception.GlobalException;
import social.connectus.gatherservice.common.exception.ResourceNotFoundException;
import social.connectus.gatherservice.domain.command.WantJoinGatherCommand;
import social.connectus.gatherservice.domain.model.Candidate;
import social.connectus.gatherservice.domain.model.Gather;
import social.connectus.gatherservice.domain.ports.inbound.WantJoinGatherUseCase;
import social.connectus.gatherservice.domain.ports.outbound.GetGatherIdPort;
import social.connectus.gatherservice.domain.ports.outbound.WantJoinGatherPort;

@AllArgsConstructor
@UseCase
public class WantJoinGatherService implements WantJoinGatherUseCase {
    private final ModelMapper modelMapper;
    GetGatherIdPort getGatherIdPort;
    WantJoinGatherPort wantJoinGatherPort;

    @Override
    public void wantJoinGather(WantJoinGatherRequest request) throws ResourceNotFoundException, ClosedGatherException, GlobalException {
        long gatherId = request.getGatherId();
        long userId = request.getUserId();

        Gather gather = getGatherIdPort.getGatherById(gatherId)
                .orElseThrow(()-> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));
        if(gather.isClosed())
            throw new ClosedGatherException(GatherConstants.ALREADY_CLOSED_GATHER + gatherId);
        
        // requsetToCommand 필요!!
        WantJoinGatherCommand command = modelMapper.map(request, WantJoinGatherCommand.class);
        wantJoinGatherPort.wantJoinGather(command);

//        Candidate candidate = new Candidate(userId, gather);
//        wantJoinGatherPort.wantJoinGather(candidate);
    }
}
