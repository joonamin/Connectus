package social.connectus.userservice.common.exception;

public class FailedToLoginException extends RuntimeException {
	public FailedToLoginException(String message) {
		super(message);
	}

	public FailedToLoginException(String message, Throwable cause) {
		super(message, cause);
	}
}
