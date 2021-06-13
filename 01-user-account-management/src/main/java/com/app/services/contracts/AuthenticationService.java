package com.app.services.contracts;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.entities.UserProfileEntity;
import com.app.models.UserProfile;
import com.app.models.ui.requests.LoginRequestModel;
import com.app.models.ui.response.LoginResponseModel;

public interface AuthenticationService extends UserDetailsService, ServiceUtilties{
	UserProfileEntity findByUsername(String username);
	LoginResponseModel login(LoginRequestModel loginRequest) throws Exception;
	String register(UserProfile model);
	boolean checkUserNameforExistence(String username);
}
