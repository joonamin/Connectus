package social.connectus.gatherservice.domain.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import social.connectus.gatherservice.application.rest.response.GetGatherResponse;
import social.connectus.gatherservice.common.constants.GatherConstants;
import social.connectus.gatherservice.common.customannotations.UseCase;
import social.connectus.gatherservice.common.exception.ResourceNotFoundException;
import social.connectus.gatherservice.domain.model.Candidate;
import social.connectus.gatherservice.domain.model.Gather;
import social.connectus.gatherservice.domain.model.Joiner;
import social.connectus.gatherservice.domain.ports.inbound.GetGatherUseCase;
import social.connectus.gatherservice.domain.ports.outbound.GetGatherIdPort;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@UseCase
public class GetGatherService implements GetGatherUseCase {
    private final ModelMapper modelMapper;
    private GetGatherIdPort getGatherIdPort;

    @Override
    public GetGatherResponse getGather(long gatherId) throws ResourceNotFoundException {
        Gather gather = getGatherIdPort.getGatherById(gatherId)
                .orElseThrow(() -> new ResourceNotFoundException(GatherConstants.GATHER_NOT_FOUND + gatherId));
        return modelMapper.map(gather, GetGatherResponse.class);

//        List<Long> candidateUserList = new ArrayList<>();
//        for(Candidate candidate : gather.getCandidateList()){
//            candidateUserList.add(candidate.getUserId());
//        }
//        getGatherResponse.setCandidateList(candidateUserList);
//
//        List<Long> joinerUserList = new ArrayList<>();
//        for(Joiner joiner : gather.getJoinerList()){
//            joinerUserList.add(joiner.getUserId());
//        }
//        getGatherResponse.setJoinerList(joinerUserList);
//        return getGatherResponse;
    }
}
