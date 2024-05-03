package com.social.eventservice.application.rest.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.eventservice.application.rest.request.MakeEventRequest;
import com.social.eventservice.domain.port.inbound.EventUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

	private final EventUseCase eventUseCase;

	@PostMapping("")
	public ResponseEntity<Void> makeEvent(MakeEventRequest request) throws IOException {
		eventUseCase.makeEvent(request);
		return ResponseEntity.accepted().build();
	}

}
