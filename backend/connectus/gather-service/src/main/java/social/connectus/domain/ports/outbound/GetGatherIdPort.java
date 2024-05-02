package social.connectus.domain.ports.outbound;

import social.connectus.domain.model.Gather;

import java.util.Optional;

public interface GetGatherIdPort {
    Optional<Gather> getGatherById(long gather_id);
}
