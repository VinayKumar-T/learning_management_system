package com.te.lms.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ApiResponse<T> {
	private  String message;
	private  LocalDateTime date=LocalDateTime.now();
	private  T data;
	public ApiResponse(String message, T data) {
		super();
		this.message = message;
		this.data = data;
	}
	

}
