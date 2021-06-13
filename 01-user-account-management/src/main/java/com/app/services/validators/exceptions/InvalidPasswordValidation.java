package com.app.services.validators.exceptions;

public class InvalidPasswordValidation extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidPasswordValidation() {
		super();
	}
	public InvalidPasswordValidation(String message) {
		super(message);
	}
}
