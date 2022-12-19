package com.te.lms.exception;

public class BatchesNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BatchesNotFoundException(String message) {
		super(message);
	}
}