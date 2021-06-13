package com.app.services.validators.impls;

import java.util.Objects;
import java.util.logging.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.app.models.UserProfile;
import com.app.services.validators.PasswordConfirmPasswordMatch;
import com.app.services.validators.exceptions.InvalidPasswordValidation;

public class PasswordConfirmPasswordValidatorImpl implements ConstraintValidator<PasswordConfirmPasswordMatch, UserProfile>{
	Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public boolean isValid(UserProfile userProfile, ConstraintValidatorContext context) {
		logger.info("userProfile :"+userProfile);
		
		if(Objects.isNull(userProfile)) throw new InvalidPasswordValidation("User Profile instance is null!");
		logger.info("userProfile Password:"+userProfile.getPassword()+" :: Confirm Password :"+userProfile.getConfirmPassword());
		
		return userProfile.getPassword().equals(userProfile.getConfirmPassword());
	}
}
