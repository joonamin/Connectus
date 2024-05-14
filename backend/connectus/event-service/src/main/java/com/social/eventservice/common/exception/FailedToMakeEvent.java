package com.social.eventservice.common.exception;

public class FailedToMakeEvent extends RuntimeException {
	public FailedToMakeEvent(String message, Throwable cause) {
		super(message, cause);
	}
}
