package social.connectus.domain.services;

import lombok.AllArgsConstructor;
import social.connectus.common.customannotations.UseCase;
import social.connectus.domain.model.Gather;
import social.connectus.domain.ports.inbound.CreateGatherUseCase;
import social.connectus.domain.ports.outbound.CreateGatherPort;

@AllArgsConstructor
@UseCase
public class CreateGatherService implements CreateGatherUseCase {
    CreateGatherPort createGatherPort;
    @Override
    public void createGather(Gather gather) {
        createGatherPort.createGather(gather);
    }
}
