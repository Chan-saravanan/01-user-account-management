package com.app.controllers;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.common.exceptions.handlers.user.UsernameAlreadyExistException;
import com.app.models.UserProfile;
import com.app.models.ui.requests.LoginRequestModel;
import com.app.models.ui.response.LoginResponseModel;
import com.app.services.contracts.AuthenticationService;

@RestController
@RequestMapping("/account")
public class AuthenticationController{
	private final Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@GetMapping("/login")
	public ResponseEntity<LoginResponseModel> login(@RequestBody LoginRequestModel loginRequestInfo){
		logger.info("Inside the loginRequestInfo");
		LoginResponseModel response = null;
		
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@PostMapping(path= {"/create","/register"})
	protected ResponseEntity<String> createUser(@Valid @RequestBody UserProfile userProfile){
		logger.info("User Profile :"+userProfile);
		//Check the username or email already exist in the user_profile table!
		
		if(authenticationService.checkUserNameforExistence(userProfile.getUsername()))
			throw new UsernameAlreadyExistException("username:"+userProfile.getUsername()+" already exists!");
		
		//Register the user account info!
		String userId = authenticationService.register(userProfile);
		logger.info("User id :"+userId);
		
		return new ResponseEntity<>(userId, HttpStatus.CREATED);
	}
}
