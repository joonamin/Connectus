package com.social.eventservice.common.exception.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.social.eventservice.common.exception.FailedToMakeEvent;
import com.social.eventservice.common.exception.NotFoundException;
import com.social.eventservice.common.exception.SaveEntityException;
import com.social.eventservice.common.exception.SavePositionException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Void> handleNotFoundException(NotFoundException e) {
		log.error("Not Found Event");
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(FailedToMakeEvent.class)
	public ResponseEntity<Void> handleFailedToMakeEventException(FailedToMakeEvent e) {
		log.error("Can't make event ==> {}", e.getMessage());
		return ResponseEntity.unprocessableEntity().build();
	}

	@ExceptionHandler(SaveEntityException.class)
	public ResponseEntity<Void> handleSaveEntityException(SaveEntityException e) {
		log.error("Can't save entity ==> {}", e.getMessage());
		return ResponseEntity.unprocessableEntity().build();
	}

	@ExceptionHandler(SavePositionException.class)
	public ResponseEntity<Void> handleFailedToMakeEventException(SavePositionException e) {
		log.error("Can't save position ==> {}", e.getMessage());
		return ResponseEntity.unprocessableEntity().build();
	}

}
