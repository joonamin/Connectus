package social.connectus.userservice.common.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import social.connectus.userservice.common.exception.FailedToLoginException;
import social.connectus.userservice.common.exception.FailedToRegisterUserException;
import social.connectus.userservice.common.exception.NotFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
		log.error("NOT FOUND EXCEPTION ===> ", ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(FailedToLoginException.class)
	public ResponseEntity<String> handleFailedToLoginException(FailedToLoginException ex) {
		log.error("FAILED TO LOGIN EXCEPTION ===> ", ex);
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
	}

	@ExceptionHandler(FailedToRegisterUserException.class)
	public ResponseEntity<String> handleFailedToRegisterUserException(FailedToRegisterUserException ex) {
		log.error("FAILED TO REGISTER EXCEPTION ===> ", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

}
