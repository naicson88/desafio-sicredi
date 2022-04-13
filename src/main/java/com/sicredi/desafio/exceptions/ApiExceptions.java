package com.sicredi.desafio.exceptions;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ApiExceptions {
	
	private final String msg;
	private final HttpStatus httpStatus;
	private final ZonedDateTime time;
	private  int httpCode;
	
//	public ApiExceptions(String msg, Throwable cause, HttpStatus httpStatus, ZonedDateTime time) {
//		super();
//		this.msg = msg;
//		this.cause = cause;
//		this.httpStatus = httpStatus;
//		this.time = time;
//	}
	
	public ApiExceptions(String msg, int httpCode, HttpStatus httpStatus, ZonedDateTime time) {
		this.msg = msg;
		this.httpCode = httpCode;
		this.httpStatus = httpStatus;
		this.time = time;	
	}
	
	public ApiExceptions(String msg, HttpStatus httpStatus, ZonedDateTime time) {
		this.msg = msg;
		this.httpStatus = httpStatus;
		this.time = time;	
	}
	
	public String getMsg() {
		return msg;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public ZonedDateTime getTime() {
		return time;
	}

	public int getHttpCode() {
		return httpCode;
	}
	
}
