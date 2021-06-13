package com.app.configurations.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4160554180340459868L;
	private Collection<SimpleGrantedAuthority> authorities;
	private String username;
	private String password;
	private Boolean enabled = true;
	
	public UserDetailsImpl(String username, String password, Boolean enabled, Collection<SimpleGrantedAuthority> authorities)
	{
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "UserDetailsImpl [authorities=" + authorities + ", username=" + username + ", password=" + password
				+ ", enabled=" + enabled + "]";
	}
}
