package social.connectus.domain.services;

import lombok.AllArgsConstructor;
import social.connectus.common.constants.GatherConstants;
import social.connectus.common.customannotations.UseCase;
import social.connectus.common.exception.ResourceNotFoundException;
import social.connectus.domain.model.Gather;
import social.connectus.domain.ports.inbound.GetGatherUseCase;
import social.connectus.domain.ports.outbound.GetGatherIdPort;

@AllArgsConstructor
@UseCase
public class GetGatherService implements GetGatherUseCase {
    private GetGatherIdPort getGatherIdPort;

    @Override
    public Gather getGather(long gatherId) throws ResourceNotFoundException {
        return getGatherIdPort.getGatherById(gatherId)
                .orElseThrow(() -> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));
    }
}
