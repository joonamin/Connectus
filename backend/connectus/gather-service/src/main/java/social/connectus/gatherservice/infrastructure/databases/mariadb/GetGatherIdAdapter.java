package social.connectus.gatherservice.infrastructure.databases.mariadb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import social.connectus.gatherservice.common.exception.ResourceNotFoundException;
import social.connectus.gatherservice.domain.model.Gather;
import social.connectus.gatherservice.domain.ports.outbound.GetGatherIdPort;

import java.util.Optional;

@Component
public class GetGatherIdAdapter implements GetGatherIdPort {

    @Autowired
    private GatherRepository gatherRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Optional<Gather> getGatherById(long gatherId) throws ResourceNotFoundException {
        return gatherRepository.findById(gatherId);
//                .orElseThrow(()-> new ResourceNotFoundException())

//        ModelMapper modelMapper = new ModelMapper();
//        GetGatherResponse getGatherResponse = modelMapper.map(gather, GetGatherResponse.class);
//        for(Candidate candidate : gather.getCandidateList()){
//            long userId = candidate.getUserId();
//            getGatherResponse.getCandidateList().add(userId);
//        }
//        return getGatherResponse;
    }
}
