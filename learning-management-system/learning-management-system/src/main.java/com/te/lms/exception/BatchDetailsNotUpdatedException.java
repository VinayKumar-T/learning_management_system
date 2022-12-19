package com.te.lms.exception;

public class BatchDetailsNotUpdatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BatchDetailsNotUpdatedException(String message) {
		super(message);
	}
}