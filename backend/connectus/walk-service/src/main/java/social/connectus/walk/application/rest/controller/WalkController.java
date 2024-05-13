package social.connectus.walk.application.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.connectus.walk.application.rest.request.*;
import social.connectus.walk.application.rest.response.CreateWalkResponse;
import social.connectus.walk.application.rest.response.GetAchievementsResponse;
import social.connectus.walk.application.rest.response.GetWalkResponse;
import social.connectus.walk.application.rest.response.GetWalksByUserResponse;
import social.connectus.walk.domain.command.*;
import social.connectus.walk.domain.ports.inbound.WalkUseCase;

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
        return walkUseCase.feignHealthCheck();
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
    public ResponseEntity<CreateWalkResponse> createWalk(@RequestBody CreateWalkRequest request){
        // TODO: request 검증
        return ResponseEntity.ok(walkUseCase.createWalk(CreateWalkCommand.from(request)));
    }

    @Operation(summary = "산책 기록 좋아요")
    @PutMapping("/route-like")
    public ResponseEntity<String> routeLike(@RequestBody RouteLikeRequest request){
        walkUseCase.routeLike(RouteLikeCommand.from(request));
        return ResponseEntity.ok("Like is successfully registered.");
    }

    @Operation(summary = "산책 기록 좋아요 취소")
    @PutMapping("/route-like-cancle")
    public ResponseEntity<String> routeLikeCancle(@RequestBody RouteLikeRequest request){
        walkUseCase.routeLikeCancle(RouteLikeCommand.from(request));
        return ResponseEntity.ok("Like is successfully canceled.");
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

    @Operation(summary = "위치 기준 주변 산책 기록 조회")
    @GetMapping("/position")
    public ResponseEntity<Slice<Long>> getWalksByPosition(@RequestBody GetWalksByPositionRequest request){
        Slice<Long> walkIdSlice = walkUseCase.getWalksByPosition(GetWalksByPositionCommand.from(request));
        return ResponseEntity.ok(walkIdSlice);
    }

    @Operation(summary = "산책 종료시 달성한 업적 조회")
    @GetMapping("/end-walk")
    public ResponseEntity<GetAchievementsResponse> getAchievementsByWalk(@RequestBody GetAchievementsRequest request){
        List<Long> achievementIds = walkUseCase.getAchievementsByWalk(GetAchievementsCommand.from((request)));
        return ResponseEntity.ok(GetAchievementsResponse.builder().achievementIds(achievementIds).build());
    }

}
