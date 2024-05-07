package social.connectus.walk.infrastructure.databases.mariadb;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import social.connectus.walk.domain.command.CreateWalkCommand;
import social.connectus.walk.domain.model.entity.Walk;
import social.connectus.walk.domain.ports.outbound.WalkPort;

@Component
@RequiredArgsConstructor
public class WalkAdapter implements WalkPort {

    private final ModelMapper modelMapper;
    private final WalkRepository walkRepository;

    @Override
    public Walk createWalk(CreateWalkCommand command) {
        Walk walk = Walk.builder()
                .userId(command.getUserId())
                .title(command.getTitle())
                .route(command.getRoute())
                .completedAchievement(command.getCompletedAchievement())
                .walkTime(command.getWalkTime())
                .walkDistance(command.getWalkDistance())
                .participateEvent(command.getParticipateEvent())
                .build();

        return walkRepository.save(walk);
    }
}
