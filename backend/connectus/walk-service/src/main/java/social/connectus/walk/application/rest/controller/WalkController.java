package social.connectus.walk.application.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.connectus.walk.application.rest.request.*;
import social.connectus.walk.application.rest.response.*;
import social.connectus.walk.common.utils.SliceResponse;
import social.connectus.walk.domain.command.*;
import social.connectus.walk.domain.model.entity.Route;
import social.connectus.walk.domain.model.entity.Walk;
import social.connectus.walk.domain.ports.inbound.WalkUseCase;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/walk")
@RequiredArgsConstructor
public class WalkController {

    private final WalkUseCase walkUseCase;

    @Operation(summary = "연결 확인")
    @GetMapping("/health-check")
    public String healthCheck(){
        return "It's working on Walk-Service!";
    }

    @Operation(summary = "타 서비스와의 연결 확인")
    @GetMapping("/feign-health-check")
    public String feignHealthCheck() {
        // TODO: 다른 모든 서비스의 healthCheck를 받아 json 객체로 반환
//        return walkUseCase.feignHealthCheck();
        return null;
    }

    @Operation(summary = "산책 상세조회")
    @GetMapping("/{walkId}")
    public ResponseEntity<GetWalkResponse> getWalk(@PathVariable long walkId){
        GetWalkResponse response = GetWalkResponse.from(walkUseCase.getWalkById(walkId));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "작성자 기준 산책 리스트 조회")
    @GetMapping("/user/{userId}")
    public ResponseEntity<GetWalksByUserResponse> getWalkByUser(@PathVariable long userId){
        GetWalksByUserResponse response = GetWalksByUserResponse.from(walkUseCase.getWalkByUser(userId));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "산책 기록 작성")
    @PostMapping
    public ResponseEntity<CreateWalkResponse> createWalk(@ModelAttribute CreateWalkRequest request) throws IOException {
        // TODO: request 검증
        return ResponseEntity.ok(walkUseCase.createWalk(CreateWalkCommand.from(request)));
    }

    @Operation(summary = "산책 기록 좋아요")
    @PutMapping("/route-like")
    public ResponseEntity<String> routeLike(@RequestBody RouteLikeRequest request){
        walkUseCase.routeLike(RouteLikeCommand.from(request));
        return ResponseEntity.ok("Like is successfully registered.");
    }

    @Operation(summary = "산책 기록 공유")
    @PatchMapping("/route-share")
    public ResponseEntity<String> routeShare(@RequestBody RouteShareRequest request){
        walkUseCase.routeShare(RouteShareCommand.from(request));
        return ResponseEntity.ok("Successfully shared.");
    }

    @Operation(summary = "산책 기록 공유 취소")
    @PatchMapping("/route-protect")
    public ResponseEntity<String> routeProtect(@RequestBody RouteProtectRequest request){
        walkUseCase.routeProtect(RouteProtectCommand.from(request));
        return ResponseEntity.ok("Successfully protected.");
    }

    @Operation(summary = "산책 기록에 따라가기한 유저 추가")
    @PatchMapping("/route-track")
    public ResponseEntity<String> routeTrack(@RequestBody RouteTrackRequest request){
        walkUseCase.routeTrack(RouteTrackCommand.from(request));
        return ResponseEntity.ok("Successfully tracked.");
    }

    @Operation(summary = "위치 기준 주변 산책 기록 id 조회")
    @PostMapping("/position")
    public ResponseEntity<SliceResponse<Long>> getWalkIdsByPosition(@RequestBody GetWalksByPositionRequest request){
        SliceResponse<Long> walkIdSlice = walkUseCase.getWalkIdsByPosition(GetWalksByPositionCommand.from(request));
        return ResponseEntity.ok(walkIdSlice);
    }

    @Operation(summary = "위치 기준 주변 산책 기록 조회")
    @PostMapping("/detail-position")
    public ResponseEntity<GetWalkSliceResponse> getWalksByPosition(@RequestBody GetWalksByPositionRequest request){
        Slice<Walk> walkSlice = walkUseCase.getWalksByPosition(GetWalksByPositionCommand.from(request));
        List<GetWalkResponse> walkList = walkSlice.getContent().stream()
                .map(walk->GetWalkResponse.from(walk))
                .toList();
        return ResponseEntity.ok(GetWalkSliceResponse.builder()
                        .walksList(walkList)
                        .hasNext(walkSlice.hasNext())
                        .pageNum(walkSlice.getNumber())
                        .pageSize(walkSlice.getSize())
                .build());
    }

    @Operation(summary = "산책 종료시 달성한 업적 조회")
    @GetMapping("/end-walk")
    public ResponseEntity<GetAchievementsResponse> getAchievementsByWalk(@RequestBody GetAchievementsRequest request){
        List<AchievementResponse> achievementList = walkUseCase.getAchievementsByWalk(request.getUserId(), GetAchievementsCommand.builder()
                .postCount(request.getPostCount())
                .participateEvent(request.getParticipateEvent())
                .build());
        return ResponseEntity.ok(GetAchievementsResponse.builder().achievementList(achievementList).build());
    }

}
