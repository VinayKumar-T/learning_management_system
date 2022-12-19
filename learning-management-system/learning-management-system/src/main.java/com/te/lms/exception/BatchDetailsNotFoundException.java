package com.te.lms.exception;

public class BatchDetailsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BatchDetailsNotFoundException(String message) {
		super(message);
	}
}