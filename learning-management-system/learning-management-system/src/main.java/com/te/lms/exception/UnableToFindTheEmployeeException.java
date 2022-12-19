package com.te.lms.exception;

public class UnableToFindTheEmployeeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnableToFindTheEmployeeException(String message) {
		super(message);
	}
}