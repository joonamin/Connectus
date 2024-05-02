package social.connectus.domain.services;

import lombok.AllArgsConstructor;
import social.connectus.application.rest.request.CloseGatherRequest;
import social.connectus.common.constants.GatherConstants;
import social.connectus.common.customannotations.UseCase;
import social.connectus.common.exception.ClosedGatherException;
import social.connectus.common.exception.GlobalException;
import social.connectus.common.exception.InvalidHostIdException;
import social.connectus.common.exception.ResourceNotFoundException;
import social.connectus.domain.model.Gather;
import social.connectus.domain.ports.inbound.CloseGatherUseCase;
import social.connectus.domain.ports.outbound.CloseGatherPort;
import social.connectus.domain.ports.outbound.GetGatherIdPort;

@AllArgsConstructor
@UseCase
public class CloseGatherService implements CloseGatherUseCase {
    GetGatherIdPort getGatherIdPort;
    CloseGatherPort closeGatherPort;
    @Override
    public void closeGather(CloseGatherRequest closeGatherRequest) throws ResourceNotFoundException, ClosedGatherException, InvalidHostIdException {
        long gather_id = closeGatherRequest.getGather_id();
        long user_id = closeGatherRequest.getUser_id();
        // gather의 주최자가 user_id인지 확인
        Gather gather = getGatherIdPort.getGatherById(gather_id)
                        .orElseThrow(()-> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gather_id));

        if(gather.isClosed())
            throw new ClosedGatherException(GatherConstants.ALREADY_CLOSED_GATHER + gather_id);
        if(gather.getHostId() != user_id)
            throw new InvalidHostIdException(GatherConstants.INVALID_HOST_ID + user_id);

        closeGatherPort.closeGather(gather);
    }
}
