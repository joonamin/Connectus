package social.connectus.userservice.common.exception;

public class FailedToRegisterUserException extends RuntimeException {

	public FailedToRegisterUserException(String message) {
		super(message);
	}

	public FailedToRegisterUserException(String message, Throwable cause) {
		super(message, cause);
	}
}
