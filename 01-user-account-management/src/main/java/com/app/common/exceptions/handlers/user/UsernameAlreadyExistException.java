package com.app.common.exceptions.handlers.user;

public class UsernameAlreadyExistException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8020517554011191977L;

	public UsernameAlreadyExistException() {
		super();
	}
	
	public UsernameAlreadyExistException(String message) {
		super(message);
	}
}
