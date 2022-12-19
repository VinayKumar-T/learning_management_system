package com.te.lms.exception.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.google.common.collect.Maps;
import com.te.lms.exception.BatchDetailsNotFoundException;
import com.te.lms.exception.BatchDetailsNotUpdatedException;
import com.te.lms.exception.BatchesNotFoundException;
import com.te.lms.exception.EmployeeCannotBeApprovedException;
import com.te.lms.exception.EmployeeNotFoundExcpetion;
import com.te.lms.exception.NoDataFoundInTheListException;
import com.te.lms.exception.NoMentorsFoundException;
import com.te.lms.exception.RegistrationFailedException;
import com.te.lms.exception.UnableToDeleteBatchException;
import com.te.lms.exception.UnableToDeleteMentorException;
import com.te.lms.exception.UnableToFindTheEmployeeException;
import com.te.lms.exception.UnableToUpdateMentorException;

@RestControllerAdvice
public class LmsExceptionHandler {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BatchDetailsNotUpdatedException.class)
	public Map<String, String> handler(BatchDetailsNotUpdatedException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UnableToDeleteBatchException.class)
	public Map<String, String> handler(UnableToDeleteBatchException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UnableToFindTheEmployeeException.class)
	public Map<String, String> handler(UnableToFindTheEmployeeException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BatchesNotFoundException.class)
	public Map<String, String> handler(BatchesNotFoundException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(EmployeeCannotBeApprovedException.class)
	public Map<String, String> handler(EmployeeCannotBeApprovedException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoMentorsFoundException.class)
	public Map<String, String> handler(NoMentorsFoundException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(EmployeeNotFoundExcpetion.class)
	public Map<String, String> handler(EmployeeNotFoundExcpetion ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RegistrationFailedException.class)
	public Map<String, String> handler(RegistrationFailedException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BatchDetailsNotFoundException.class)
	public Map<String, String> handler(BatchDetailsNotFoundException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoDataFoundInTheListException.class)
	public Map<String, String> handler(NoDataFoundInTheListException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UnableToUpdateMentorException.class)
	public Map<String, String> handler(UnableToUpdateMentorException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UnableToDeleteMentorException.class)
	public Map<String, String> handler(UnableToDeleteMentorException ex) {
		Map<String, String> map = Maps.newHashMap();
		map.put("message", ex.getMessage());
		return map;
	}
}