package com.social.eventservice.common.exception;

public class SavePositionException extends SaveEntityException {
	private static final String DEFAULT_ERROR = "failed to save position";

	public SavePositionException() {
		super(DEFAULT_ERROR);
	}

	public SavePositionException(String message) {
		super(message);
	}

	public SavePositionException(String message, Throwable cause) {
		super(message, cause);
	}

}
