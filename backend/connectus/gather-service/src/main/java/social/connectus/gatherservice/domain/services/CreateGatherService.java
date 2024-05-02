package social.connectus.gatherservice.domain.services;

import lombok.AllArgsConstructor;
import social.connectus.gatherservice.common.customannotations.UseCase;
import social.connectus.gatherservice.domain.model.Gather;
import social.connectus.gatherservice.domain.ports.inbound.CreateGatherUseCase;
import social.connectus.gatherservice.domain.ports.outbound.CreateGatherPort;

@AllArgsConstructor
@UseCase
public class CreateGatherService implements CreateGatherUseCase {
    CreateGatherPort createGatherPort;
    @Override
    public void createGather(Gather gather) {
        createGatherPort.createGather(gather);
    }
}
