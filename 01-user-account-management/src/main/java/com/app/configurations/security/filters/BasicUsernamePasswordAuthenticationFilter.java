package com.app.configurations.security.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.models.ui.requests.LoginRequestModel;
import com.app.services.contracts.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BasicUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private final Logger logger = Logger.getLogger(getClass().getName());

	public BasicUsernamePasswordAuthenticationFilter(AuthenticationService authenticationService, Environment env, AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		logger.info("Inside the attempt authentication action!");
		
		try {
			LoginRequestModel loginModel = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModel.class);
			logger.info("Calling authenticate method on authentication manager!");
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword(), new ArrayList<>()));
		} catch (Exception e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		logger.info("Inside the  successfull authentication!");
		
		logger.info("authentication result :"+authResult);
		logger.info("authenticated user detail :"+authResult.getPrincipal());
		logger.info("authenticated user authorities: "+authResult.getAuthorities());
		
		response.addHeader("test1", "test1");
		response.addHeader("userId", "test2");
	}
}
