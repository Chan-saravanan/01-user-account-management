package com.app.services.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.app.services.validators.impls.PasswordConfirmPasswordValidatorImpl;

@Documented
@Constraint(validatedBy = PasswordConfirmPasswordValidatorImpl.class)
@Target( { ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConfirmPasswordMatch {
	String message() default "Password and confirm Password does not match!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
