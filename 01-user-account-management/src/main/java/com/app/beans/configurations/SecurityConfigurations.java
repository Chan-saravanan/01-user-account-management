package com.app.beans.configurations;

import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfigurations {
	Logger logger = Logger.getLogger(getClass().getName());
	
	@Bean(name="customDaoAuthenticationProvider")
	public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailService){
		logger.info("Inside the customDaoAuthenticationProvider");
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setUserDetailsService(userDetailService);
		
		return daoAuthenticationProvider;
	}
}
