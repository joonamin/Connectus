package social.connectus.gatherservice.domain.ports.outbound;


import social.connectus.gatherservice.domain.model.Gather;

public interface CreateGatherPort {
    void createGather(Gather gather);
}