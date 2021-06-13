package com.app.configurations.security;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.app.configurations.security.filters.BasicUsernamePasswordAuthenticationFilter;
import com.app.services.contracts.AuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private Environment env;
	
	@Autowired
	@Qualifier("customDaoAuthenticationProvider")
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("Configuring the http security!");
		http
		.authorizeRequests()
		.antMatchers("/account/**").permitAll()
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST).hasIpAddress(env.getProperty("gateway.ip-address"))
		.and()
		.addFilter(getAuthenticationFilter());
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		logger.info("Configuring the http security authentication builder!");
		authenticationManagerBuilder.authenticationProvider(authenticationProvider);
	}
	
	private BasicUsernamePasswordAuthenticationFilter getAuthenticationFilter() throws Exception{
		BasicUsernamePasswordAuthenticationFilter  filter = new BasicUsernamePasswordAuthenticationFilter(authenticationService, env, authenticationManager());
		filter.setFilterProcessesUrl(env.getProperty("login.url.path"));
		return filter;
	}
}
