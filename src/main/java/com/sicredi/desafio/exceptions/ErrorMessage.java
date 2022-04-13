package com.sicredi.desafio.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorMessage extends RuntimeException {
	
	private static final long serialVersionUID = 1149241039409861914L;
	
	private HttpStatus httpStatus;
	private String msg;
	
	
	
	public ErrorMessage(HttpStatus httpStatus, String msg) {
		super();
		this.setHttpStatus(httpStatus);
		this.msg = msg;
	}

	public ErrorMessage(String msg) {
		super(msg);
	}
	
	public ErrorMessage(String msg, Throwable cause) {
		super(msg, cause);
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
}