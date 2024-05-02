package social.connectus.gatherservice.domain.services;

import lombok.AllArgsConstructor;
import social.connectus.gatherservice.application.rest.request.CloseGatherRequest;
import social.connectus.gatherservice.common.constants.GatherConstants;
import social.connectus.gatherservice.common.customannotations.UseCase;
import social.connectus.gatherservice.common.exception.ClosedGatherException;
import social.connectus.gatherservice.common.exception.InvalidHostIdException;
import social.connectus.gatherservice.common.exception.ResourceNotFoundException;
import social.connectus.gatherservice.domain.model.Gather;
import social.connectus.gatherservice.domain.ports.inbound.CloseGatherUseCase;
import social.connectus.gatherservice.domain.ports.outbound.CloseGatherPort;
import social.connectus.gatherservice.domain.ports.outbound.GetGatherIdPort;

@AllArgsConstructor
@UseCase
public class CloseGatherService implements CloseGatherUseCase {
    GetGatherIdPort getGatherIdPort;
    CloseGatherPort closeGatherPort;
    @Override
    public void closeGather(CloseGatherRequest request) throws ResourceNotFoundException, ClosedGatherException, InvalidHostIdException {
        long gatherId = request.getGatherId();
        long userId = request.getUserId();
        // gather의 주최자가 user_id인지 확인
        Gather gather = getGatherIdPort.getGatherById(gatherId)
                        .orElseThrow(()-> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));

        if(gather.isClosed())
            throw new ClosedGatherException(GatherConstants.ALREADY_CLOSED_GATHER + gatherId);
        if(gather.getHostId() != userId)
            throw new InvalidHostIdException(GatherConstants.INVALID_HOST_ID + userId);

        closeGatherPort.closeGather(request);
    }
}
