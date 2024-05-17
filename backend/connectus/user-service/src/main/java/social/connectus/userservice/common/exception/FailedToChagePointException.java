package social.connectus.userservice.common.exception;

public class FailedToChagePointException extends RuntimeException {
	public FailedToChagePointException(String message) {
		super(message);
	}

	public FailedToChagePointException(String message, Throwable cause) {
		super(message, cause);
	}
}
