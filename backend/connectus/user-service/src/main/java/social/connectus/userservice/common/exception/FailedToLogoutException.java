package social.connectus.userservice.common.exception;

public class FailedToLogoutException extends RuntimeException {

	public FailedToLogoutException(String message) {
		super(message);
	}

	public FailedToLogoutException(String message, Throwable cause) {
		super(message, cause);
	}
}
