package com.te.lms.exception;

public class UnableToUpdateBatchException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnableToUpdateBatchException(String message) {
		super(message);
	}
}