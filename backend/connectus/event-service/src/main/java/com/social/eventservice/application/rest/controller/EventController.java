package com.social.eventservice.application.rest.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.social.eventservice.application.rest.request.MakeEventRequest;
import com.social.eventservice.application.rest.request.SpreadPingsRequest;
import com.social.eventservice.application.rest.response.EventDetailsResponse;
import com.social.eventservice.application.rest.response.PingsDetailsResponse;
import com.social.eventservice.domain.port.inbound.EventUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

	private final EventUseCase eventUseCase;

	@PostMapping("")
	public ResponseEntity<Void> makeEvent(@RequestBody MakeEventRequest request) throws IOException {
		eventUseCase.makeEvent(request);
		return ResponseEntity.accepted().build();
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<EventDetailsResponse> getDetails(@PathVariable Long id) {
		return ResponseEntity.ok(eventUseCase.detailsEvent(id));
	}

	@GetMapping("/pings")
	public ResponseEntity<List<PingsDetailsResponse>> getSpreadedPings(@RequestParam SpreadPingsRequest request) {
		return ResponseEntity.ok(eventUseCase.spreadPings(request.getUserId(), request.getEventId()));
	}

}
