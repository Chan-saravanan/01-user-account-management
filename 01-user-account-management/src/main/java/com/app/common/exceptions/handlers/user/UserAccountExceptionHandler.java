package com.app.common.exceptions.handlers.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.common.errors.messages.ErrorMessage;
import com.app.services.validators.exceptions.InvalidPasswordValidation;


@ControllerAdvice
public class UserAccountExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<ErrorMessage> handlePasswordMisMatchValidation(InvalidPasswordValidation exception)
	{
		ErrorMessage message = new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorMessage> handleException(UsernameAlreadyExistException exception)
	{
		ErrorMessage message = new ErrorMessage(exception.getMessage(), HttpStatus.IM_USED);
		
		return new ResponseEntity<>(message, HttpStatus.IM_USED);
	}
}
