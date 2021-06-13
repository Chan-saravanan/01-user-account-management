package com.app.common.errors.messages;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
	private String message;
	private Integer status;
	private long time;
	
	public ErrorMessage(String message, HttpStatus httpStatus) {
		this.message = message;
		status = httpStatus.value();
		time = new Date().getTime();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public long getTime() {
		return time;
	}
}
