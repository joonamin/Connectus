package com.social.eventservice.application.rest.controller;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.social.eventservice.application.rest.request.MakeEventRequest;
import com.social.eventservice.application.rest.request.SpreadPingsRequest;
import com.social.eventservice.application.rest.response.EventDetailsResponse;
import com.social.eventservice.application.rest.response.PingsDetailsResponse;
import com.social.eventservice.common.type.Position;
import com.social.eventservice.domain.port.inbound.EventUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
@Slf4j
public class EventController {

	private final EventUseCase eventUseCase;

	@PostMapping(value = ""/*, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}*/)
	public ResponseEntity<Void> makeEvent(@ModelAttribute MakeEventRequest request) throws IOException {
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

	// TODO: data bind
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(List.class, "positions", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				ObjectMapper objectMapper = new ObjectMapper();
				log.info("@@@@@@ text: {} ", text);
				try {

					List<Position> positions = objectMapper.readValue(text, new TypeReference<List<Position>>() {
					});
					setValue(positions);
				} catch (JsonProcessingException e) {
					throw new IllegalArgumentException("invalid <position> format!!!", e);
				}
			}
		});
	}

}
