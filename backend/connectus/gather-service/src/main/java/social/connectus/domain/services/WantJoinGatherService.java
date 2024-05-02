package social.connectus.domain.services;

import lombok.AllArgsConstructor;
import social.connectus.application.rest.request.WantJoinGatherRequest;
import social.connectus.common.constants.GatherConstants;
import social.connectus.common.customannotations.UseCase;
import social.connectus.common.exception.ClosedGatherException;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.exception.ResourceNotFoundException;
import social.connectus.domain.model.Candidate;
import social.connectus.domain.model.Gather;
import social.connectus.domain.ports.inbound.WantJoinGatherUseCase;
import social.connectus.domain.ports.outbound.GetCandidateUserIdGatherPort;
import social.connectus.domain.ports.outbound.GetGatherIdPort;
import social.connectus.domain.ports.outbound.WantJoinGatherPort;

@AllArgsConstructor
@UseCase
public class WantJoinGatherService implements WantJoinGatherUseCase {
    GetGatherIdPort getGatherIdPort;
    GetCandidateUserIdGatherPort getCandidateUserIdGatherPort;
    WantJoinGatherPort wantJoinGatherPort;

    @Override
    public void wantJoinGather(WantJoinGatherRequest wantJoinGatherRequest) throws ResourceNotFoundException, ClosedGatherException, GlobalException {
        long gatherId = wantJoinGatherRequest.getGatherId();
        long userId = wantJoinGatherRequest.getUserId();

        Gather gather = getGatherIdPort.getGatherById(gatherId)
                .orElseThrow(()-> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));
        if(gather.isClosed())
            throw new ClosedGatherException(GatherConstants.ALREADY_CLOSED_GATHER + gatherId);

        Candidate candidate = new Candidate(userId, gather);
        wantJoinGatherPort.wantJoinGather(candidate);
    }
}
